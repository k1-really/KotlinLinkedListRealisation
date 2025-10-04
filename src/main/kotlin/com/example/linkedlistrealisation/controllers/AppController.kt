package com.example.linkedlistrealisation.controllers

import com.example.linkedlistrealisation.dataStructures.LinkedListRealisation
import com.example.linkedlistrealisation.factories.TypeFactory
import com.example.linkedlistrealisation.interfaces.UserTypeInterface
import javafx.event.EventHandler
import javafx.fxml.FXML
import javafx.scene.control.*
import javafx.scene.layout.HBox
import javafx.scene.layout.VBox
import javafx.scene.text.Font
import javafx.scene.text.FontWeight
import java.io.*


class AppController {
    lateinit var menuType1: Menu

    @FXML
    var addToEndInput: TextField? = null

    @FXML
    var addByIndexInput: TextField? = null

    @FXML
    var menuType: Menu? = null

    @FXML
    var removeByIndexErrorLbl: Label? = null

    @FXML
    var addByIndexErrorLbl: Label? = null

    @FXML
    var addToEndErrorLbl: Label? = null

    @FXML
    var findByIndexErrorLbl: Label? = null
    @FXML
    var addId: TextField? = null
    @FXML
    var removeId: TextField? = null
    @FXML
    var findId: TextField? = null
    @FXML
    var valueForFind: Label? = null
    @FXML
    var clearBtn: Button? = null
    @FXML
    var vBox: VBox? = null
    var hBox: HBox? = null
    @FXML
    var errorLoading: Label? = null

    @FXML
    var addToEndBtn: Button? = null

    @FXML
    var addByIndexBtn: Button? = null

    @FXML
    var removeByIndexBtn: Button? = null

    @FXML
    var findByIndexBtn: Button? = null
    private var currentUserType: UserTypeInterface? = null
    private val typeFactory: TypeFactory = TypeFactory()
    var stringBuilder: StringBuilder? = null
    var linkedListRealisation: LinkedListRealisation<Any?>? = null
    @FXML
    private fun initialize() {
        disableManagingBtn()
        showTypes()
        chooseType()
    }

    @FXML
    private fun addToEnd() {
        disableErrorLabels()
        val valueToAdd = addToEndInput!!.text
        if (valueToAdd.isEmpty()) {
            addToEndErrorLbl!!.isVisible = true
            addToEndErrorLbl!!.text = "Empty input!"
        } else {
            val obj = currentUserType!!.parseValueDeser(valueToAdd)
            if (obj != null) {
                linkedListRealisation!!.add(obj)
                drawList()
            } else {
                addToEndErrorLbl!!.isVisible = true
                addToEndErrorLbl!!.text = "Неверный формат!"
            }
            addToEndErrorLbl!!.isVisible = false
        }
    }

    @FXML
    private fun addByIndex() {
        disableErrorLabels()
        val valueToAdd = addByIndexInput!!.text
        val index = addId!!.text
        if (valueToAdd.isEmpty() || index.isEmpty()) {
            addByIndexErrorLbl!!.isVisible = true
            addByIndexErrorLbl!!.text = "Empty input!"
        } else {
            val obj = currentUserType!!.parseValueDeser(valueToAdd)

            linkedListRealisation!!.addByIndex(index.toInt(), obj)
            drawList()
            addByIndexErrorLbl!!.isVisible = false
        }
    }

    @FXML
    private fun removeByIndex() {
        disableErrorLabels()
        val indexToRemove = removeId!!.text
        if (indexToRemove.isEmpty()) {
            removeByIndexErrorLbl!!.isVisible = true
            removeByIndexErrorLbl!!.text = "Empty input!"
        } else {
            linkedListRealisation!!.removeByIndex(indexToRemove.toInt())
            drawList()
            addToEndErrorLbl!!.isVisible = false
        }
    }

    @FXML
    private fun findByIndex() {
        disableErrorLabels()
        val indexToFind = findId!!.text
        if (indexToFind.isEmpty()) {
            findByIndexErrorLbl!!.isVisible = true
            findByIndexErrorLbl!!.text = "Empty input!"
        } else {
            val str = linkedListRealisation!![indexToFind.toInt()].toString()
            valueForFind!!.isVisible = true
            valueForFind!!.text = str
            findByIndexErrorLbl!!.isVisible = false
        }
    }

    @FXML
    private fun clearList() {
        linkedListRealisation!!.clear()
        drawList()
    }

    @FXML
    private fun sortList() {
        val comparator: Comparator<Any>? = currentUserType!!.getTypeComparator()
        linkedListRealisation!!.sort(comparator!!)
        drawList()
    }

    private fun chooseType() {
        menuType?.onAction = EventHandler { actionEvent ->
            val item = actionEvent.target as MenuItem
            linkedListRealisation = LinkedListRealisation()
            //drawList()
            currentUserType = typeFactory.getBuilderByName(item.text)
            for (menuItem in menuType!!.items) {
                menuItem.isDisable = false
            }
            item.isDisable = true
            enableManagingBtn()
        }
    }

    private fun showTypes() {
        var i = 0
        for (s in typeFactory.typeNameList) {
            val itemToAdd = MenuItem(s)
            itemToAdd.id = "item$i"
            menuType!!.items.add(itemToAdd)
            i++
        }
    }

    fun drawList() {
        vBox?.children?.clear()
        linkedListRealisation?.forEach { elem: Any? ->
            val str = currentUserType!!.readValueSer(elem)
            val label = Label(str)
            label.font = Font.font("Roboto", FontWeight.EXTRA_BOLD, 24.0)
            vBox?.children?.add(label)
        }


    }

    private fun disableManagingBtn() {
        addToEndBtn!!.isDisable = true
        addByIndexBtn!!.isDisable = true
        removeByIndexBtn!!.isDisable = true
        findByIndexBtn!!.isDisable = true
    }

    private fun enableManagingBtn() {
        addToEndBtn!!.isDisable = false
        addByIndexBtn!!.isDisable = false
        removeByIndexBtn!!.isDisable = false
        findByIndexBtn!!.isDisable = false
    }

    private fun disableErrorLabels() {
        addToEndErrorLbl!!.isVisible = false
        addByIndexErrorLbl!!.isVisible = false
        removeByIndexErrorLbl!!.isVisible = false
        findByIndexErrorLbl!!.isVisible = false
    }

    fun saveToFile() {
        try {
            val fileWriter = FileWriter(File("save.txt"))
            fileWriter.write(currentUserType!!.typeName() + "\n")
            for (i in 0 until linkedListRealisation!!.size) { //Задействовать foreach
                fileWriter.write(linkedListRealisation!![i].toString() + "\n")
            }
            fileWriter.close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun loadFromFile() {
        linkedListRealisation!!.clear()
        errorLoading!!.isVisible = false
        val file = "save.txt"
        val i = 0
        //UserTypeInterface userType;
        try {
            val br = BufferedReader(FileReader(file))
            val type: String
            var str: String?
            type = br.readLine()
            if (type != currentUserType!!.typeName()) {
                errorLoading!!.isVisible = true
                linkedListRealisation!!.clear()
                return
            }
            while (br.readLine().also { str = it } != null) {
                linkedListRealisation!!.add(currentUserType!!.parseValueDeser(str))
            }
            drawList()
        } catch (e: FileNotFoundException) {
            throw RuntimeException(e)
        } catch (e: IOException) {
            throw RuntimeException(e)
        }
    }
}

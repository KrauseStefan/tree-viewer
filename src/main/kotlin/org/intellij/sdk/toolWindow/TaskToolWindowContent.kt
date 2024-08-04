package org.intellij.sdk.toolWindow

import com.intellij.openapi.wm.ToolWindow
import com.intellij.ui.components.JBList
import java.awt.BorderLayout
import java.awt.event.ActionEvent
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import javax.swing.JButton
import javax.swing.JPanel

class TaskToolWindowContent(toolWindow: ToolWindow, private val taskService: TaskService) {
  val contentPanel: JPanel = JPanel()
  private val targetsList: JBList<*> = JBList<Any?>()

  init {
    contentPanel.layout = BorderLayout(0, 20)
    contentPanel.add(createControlsPanel(toolWindow), BorderLayout.CENTER);

    val targetList = taskTargetsList
    taskService.buildTargetList()

    targetsList.addMouseListener(object : MouseAdapter() {
      override fun mouseClicked(event: MouseEvent) {
        if (event.clickCount == 2 && event.button == MouseEvent.BUTTON1) {
          val clickedIndex = targetList.locationToIndex(event.getPoint())
          val target = targetList.model.getElementAt(clickedIndex)
        }
      }
    })
    contentPanel.add(targetList, BorderLayout.PAGE_START)
  }

  val taskTargetsList: JBList<String>
    get() {
      val targets = taskService.targets
      return JBList(*targets)
    }

  private fun createControlsPanel(toolWindow: ToolWindow): JPanel {
    val controlsPanel = JPanel()
    val hideToolWindowButton = JButton("Hide")
    hideToolWindowButton.addActionListener { _: ActionEvent? -> toolWindow.hide(null) }
    return controlsPanel
  }
}
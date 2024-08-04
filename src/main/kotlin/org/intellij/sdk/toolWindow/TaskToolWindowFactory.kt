package org.intellij.sdk.toolWindow

import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.wm.ToolWindow
import com.intellij.openapi.wm.ToolWindowFactory
import com.intellij.ui.content.ContentFactory

internal class TaskToolWindowFactory : ToolWindowFactory, DumbAware {

  override fun createToolWindowContent(project: Project, toolWindow: ToolWindow) {
    val taskService = TaskService(project)

    val toolWindowContent = TaskToolWindowContent(toolWindow, taskService)
    val content = ContentFactory.getInstance().createContent(toolWindowContent.contentPanel, "", false)
    toolWindow.contentManager.addContent(content)
  }
}

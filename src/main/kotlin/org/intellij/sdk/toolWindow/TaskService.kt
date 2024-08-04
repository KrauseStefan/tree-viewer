package org.intellij.sdk.toolWindow

import com.intellij.execution.ExecutorRegistry
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.execution.runners.ExecutionEnvironmentBuilder
import com.intellij.ide.actions.runAnything.execution.RunAnythingRunProfile
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.guessProjectDir

class TaskService(private val project: Project) {

  suspend fun buildTargetList(): List<String> {

    val executor = ExecutorRegistry.getInstance().getExecutorById(DefaultRunExecutor.EXECUTOR_ID)!!

    val cmd = GeneralCommandLine("bazel", "query", "//...")
      .withWorkDirectory(project.guessProjectDir()!!.path)
    val runAnythingRunProfile = RunAnythingRunProfile(cmd, "bazel")

    ExecutionEnvironmentBuilder.create(project, executor, runAnythingRunProfile)
      .buildAndExecute()

    return listOf("")
  }

  val targets: Array<String>
    get() {
      return arrayOf("Empty")
    }

}

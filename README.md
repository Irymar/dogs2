# Build tools task

## Part 1: Webpack

Inside `ui/webpack.config.js` file write configuration to *build* a frontend application.
* The source of the application should be `ui/src/index.js` file. The output filename of the built application should be `app.js`.
* Destination of the Webpack output should be `ui/dist` folder.
* Use [HtmlWebpackPlugin](https://webpack.js.org/plugins/html-webpack-plugin/) to copy `ui/src/index.html` to the `ui/dist` folder.
* Use [CopyWebpackPlugin](https://webpack.js.org/plugins/copy-webpack-plugin/) to copy `ui/src/style.css` to the `ui/dist/css` folder. Note the additional subfolder here.

You can run Webpack with following commands:

```bash
cd ui
npm i
npm run build
```


## Part 2: Gradle

> Warning: You need Java 17 to run the Gradle build.

Inside `build.gradle.kts` file write configuration to excute the webpack build that you configured in part 1, copy it's results and run a Java web application that serves the frontend.
* Register an `Exec` type task called `npmInstall`. This task should use `ui` folder as work directory and run the `npm i` command.
* Register an `Exec` type task called `compileUi`. This task should use `ui` folder as work directory and run the `npm run build` command. This task should depend on previous `npmInstall` task.
* Register an `Copy` type task called `copyUi`. This task should copy `ui/dist/index.html`, `ui/dist/app.js` file and `ui/dist/css` folder into `src/main/resources` folder. This task should depend on previous `compileUi` task.
* Tell Gradle that all tasks with type `org.springframework.boot.gradle.tasks.run.BootRun` should depend on previous `copyUi` task.

If you've done everything correctly, you can run the following command:

```bash
./gradlew bootRun
```

And see a cool list of dogs on [http://localhost:8080](http://localhost:8080)
# Development

When developing code for the frontend, the following tools are recommended:

- Code Editor: VS Code
- Browser: Chrome and/or Safari (since the app will later run on Chromium and the iOS-built-in WebView (WkWebView))
- NodeJS with NPM

# VSCode 

> Important: You should always directly open the "frontend" directory with VSCode, not the root. Only in this way the frontend VSCode configuration is applied.

For VSCode, please install at least the following extensions:
- Angular Language Service (https://marketplace.visualstudio.com/items?itemName=Angular.ng-template)
- Prettier (https://marketplace.visualstudio.com/items?itemName=esbenp.prettier-vscode)
- TSLint (https://marketplace.visualstudio.com/items?itemName=ms-vscode.vscode-typescript-tslint-plugin)

This project also includes some VSCode settings. To test if they work properly: Open an Angular TS file (e.g. the app.component.ts) and add, for instance, a class field `public test: number;`. The word "public" should be underlined as it is not allowed in our project. Now, save everything. Does the "public" keyword disappear? If so, then the automatic fixing is working properly, at least for the TSLint part.


# NPM

It is recommended to install the global Angular and Ionic packages:

```
npm install -g @angular/cli
```

```
npm install -g @ionic/cli
````

# Run the project

Now, you can run the project using `ng serve`. A dev server will start on `localhost:4200`.

# Linting

Before pushing a commit, make sure all of your files pass all linter and Prettier checks. To do so, simply run:

```
npm run all-checks
```

> Important: When using the CLI to generate Ionic pages, components or similar, always remember to lint + Prettierify these files as well!

**We currently don't perform any testing. So make sure you don't (accidentally) create and commit a `*.spec.ts` file!**

# Tips

You don't want to create pages (or similar) manually? The Ionic CLI can generate them for you! See https://ionicframework.com/docs/cli/commands/generate

Angular is also able to generate some files, but since we are using Ionic, you should first check whether the Ionic CLI provides an equivalent command for that.

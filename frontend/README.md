# Development

When developing code for the frontend, the following tools are recommended:

- Code Editor: VS Code
- Browser: Chrome and/or Safari (since the app will later run on Chromium and the iOS-built-in WebView (WkWebView))
- NodeJS with NPM

# VSCode 

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

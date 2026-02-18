# AI Tool Usage Documentation

I used **GitHub Copilot Pro** to assist me with the iP project.

## How I Used GitHub Copilot:

### 1. Autocomplete Suggestions

- Used autocomplete feature for method implementations based on method names alone
- Leveraged suggestions for common patterns (e.g., exception handling, validation logic)
- Accepted and modified suggestions to fit the specific requirements of the project

### 2. Code Planning and Architecture

- Asked Copilot to suggest architecture decisions
    - E.g: The module allocation of classes
- Requested suggestions for refactoring code to improve maintainability through class decomposition and separation of
  concerns

### 3. Code Refactoring

- Asked Copilot to refactor methods when there are changes in signatures or constructors
    - E.g: Refactoring the HelpCommand to accommodate changes in the constructor parameters
- Used AI agent to ensure that all references to the modified method were updated accordingly
- Used AI agent to refactor all the javadoc comments to reflect my newfound knowledge in Code Quality

### 4. Error Resolution

- Asked Copilot to fix compilation errors
- Asked Copilot to identify the sources of runtime errors and how to resolve them

### 5. Test Generation

- Generated all my test cases

## Reflections

### AI is really good at:

- **Precise tasks**: Specific, well-defined tasks like "create a way to parse X" or "refactor method Y to use Z from A,
  B, C"
- **70% completion**: Takes me most of the way there quickly
- **Pattern recognition**: Good at suggesting standard SWE patterns that I have not known myself, such as
- **Refactoring**: Effective for mechanical refactoring (renaming, moving code)

### AI is not good at:

- **Many rules**: When there are a lot of restrictions or requirements, it can struggle to keep track of them all, and
  then violate them. In this case, I would have to come in to hand code the solution.
- **FXML stuff**: I found that Copilot was not very good at generating FXML code, and I had to hand code it myself most
  of the time, probably because it is not a very common language, and there are not many examples of it in the training
  data
- **Almost there only**: AI is very good at getting me to the solution, but it always requires some manual tweaking to
  get it to work. And sometimes, the solution it suggested has so many errors that I have to spend equal amount of time
  fixing it.
- **Env and configs**: AI is not good at generating environment and configuration files, such as .gitignore,
  .editorconfig, etc.

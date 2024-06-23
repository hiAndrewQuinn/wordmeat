# Contributing to wordmeat

First off, thank you for considering contributing to wordmeat! Your contributions are highly valued.

The following is a set of guidelines for contributing to wordmeat, which is hosted in the [hiAndrewQuinn/wordmeat](https://github.com/hiAndrewQuinn/wordmeat) repository on GitHub.

## How to Contribute

### Reporting Bugs

If you find a bug, please report it by [opening an issue](https://github.com/hiAndrewQuinn/wordmeat/issues). Include as much detail as possible to help us identify and fix the issue quickly. Details should include:

- Steps to reproduce the bug
- Expected and actual results
- Screenshots, if applicable
- The version of OpenJDK and Maven you are using

### Suggesting Enhancements

If you have an idea for an enhancement, we would love to hear about it! Please [open an issue](https://github.com/hiAndrewQuinn/wordmeat/issues) and describe:

- The enhancement you would like to see
- Why you believe this enhancement would be useful
- Any examples or mockups

### Contributing Code

Any code contributed to the main repo is assumed to be under **the same license** as the repo itself, CC0 in this case.

1. **Fork the Repository**: Click the "Fork" button at the top right of the [repository page](https://github.com/hiAndrewQuinn/wordmeat).

2. **Clone Your Fork**: Clone your forked repository to your local machine.
    ```bash
    git clone https://github.com/your-username/wordmeat.git
    cd wordmeat
    ```

3. **Create a Branch**: Create a new branch for your work.
    ```bash
    git checkout -b feature-name
    ```

4. **Make Changes**: Make your changes in the new branch.

5. **Commit Changes**: Commit your changes with a clear and descriptive commit message.
    ```bash
    git commit -m "Description of the feature or fix"
    ```

6. **Push Changes**: Push your changes to your forked repository.
    ```bash
    git push origin feature-name
    ```

7. **Open a Pull Request**: Go to the original repository and open a pull request. Describe your changes and the motivation for them.

### Coding Standards

- Follow Java coding conventions.
- Write clear, concise, and descriptive commit messages.
- Ensure your code passes existing tests and add new tests for your changes.

### Running Tests

To run tests, use the following Maven command:

```bash
mvn test

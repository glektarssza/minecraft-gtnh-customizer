# Contribution Guidelines #

<!-- omit in toc -->
## Table of Contents ##

* [Contribution Guidelines](#contribution-guidelines)
    * [General](#general)
    * [Submitting Pull Requests](#submitting-pull-requests)
        * [Branch Naming Conventions](#branch-naming-conventions)
        * [Pull Request Conventions](#pull-request-conventions)
        * [Linting](#linting)

## General ##

By submitting contributions to this project you agree:

* That you own the rights to your contributions or have permission to submit the
  contributions you are making to the project at the time of submission.
* That your contributions become part of the project and belong to the project
  upon being accepted.
* That you are making contributions with no implied expectation of being
  compensated for your contributions.
    * The above is, obviously, not applicable if you have a separate agreement
      with the project administrator (G'lek Tarssza exclusively).

## Submitting Pull Requests ##

This project uses a "Fork/Branch and Pull Request" model.

To submit changes please:

1. Fork the repository if you are not a contributor.
2. Create a new branch to make your changes according to the naming conventions
   outlined in [Branch Naming Conventions](#branch-naming-conventions).
3. Make your changes.
4. Submit a pull request according to the conventions outlined in
   [PR Conventions](#pull-request-conventions).

### Branch Naming Conventions ###

Your branch name should be meaningful. For example; prefixed with the type of
change you're making, then a separator, then an issue number if appropriate, an
finally a short description with spaces replaced by dashes.

```sh
# Example bug fix branch name with issue number
"bugfix/123-fix-dup-glitch"

# Example chore branch name with no issue number
"chore/clean-up-java-docs"
```

Valid prefixes are:

* `bug`
* `bugfix`
* `chore`
* `dependabot`
* `docs`
* `feature`
* `hotfix`
* `refactor`
* `release`
* `security`
* `task`

Please note that the `release` prefix is reserved for contributors who have
permissions to create releases and the `dependabot` prefix is reserved for the
automated tooling (GitHub Dependabot) that checks and generates pull requests to
update dependencies on a weekly basis. Misuse of these branch prefixes will
result in a warning and consistent/blatant misuse of these prefixes will be
considered grounds for removal from the project/project communication channels.

### Pull Request Conventions ###

Pull requests should follow a general format as outlined below.

The title should be prefixed with `[<TYPE>]` where `<TYPE>` aligns with the type
of work the pull request covers. For example; a feature pull request should be
prefixed with `[Feature]` while a documentation pull request would use `[Doc]`,
`[Docs]`, or `[Documentation]` (though the last is the least preferred due to
length). Generally try to keep this "type tag" as short as possible while still
keeping it descriptive.

The rest of the title of your pull request should be a quick, concise summary
of the purpose of your pull request. Try to keep this portion to under 80
characters long.

The body of your pull request should clearly state:

* A brief overview of what you changed.
* The a clear, concise explanation of the reasoning behind why you changed it.
* Any "gotchas" or things to note while reviewing the pull request.
* Test cases to be run to validate the changes are working as expected.
    * These are in addition to any automated tests the may have been added.
* Ideally, but optionally, a screenshot or video of the changes working.

You should properly set your pull request's labels if you have permissions to do
so.

You should _not_ set a milestone on a pull request unless you are a project
manager.

You should assign pull request reviewers. Ideally your chosen reviewers would be
one expert in the region of code changed and one non-expert.

### Linting ###

Code must adhere to the style rules set out and enforced by the Spotless tool.

You should run `./gradlew spotlessCheck` prior to submitting pull requests. Any
pull requests which fail linting will be rejected until they pass.

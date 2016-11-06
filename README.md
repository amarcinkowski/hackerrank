# solutions framework [![Build Status](https://travis-ci.org/amarcinkowski/solutions-framework.svg?branch=master)](https://travis-ci.org/amarcinkowski/solutions-framework)

Easy usage of IDE (eclipse & IDEA) tools in solving programming challenges.

## Platform support

| feature | ![HackerRank](https://amarcinkowski.github.io/imgs/solutions-framework/hackerrank.png) HackerRank | ![codility](https://amarcinkowski.github.io/imgs/solutions-framework/codility.png) |
|---|---|---|
| list | :heavy_check_mark: | - |
| create | :heavy_check_mark: | :heavy_check_mark: |
| generate | :heavy_check_mark: | - |
| test | :heavy_check_mark: | :heavy_check_mark: |

### run.sh

```
Usage: run.sh [options] [command] [command options]
  Options:
    -h, --help
      this message
    -v, --version
      version information
  Commands:
    test      runs the solutions suite for selected platform
      Usage: test [options]
        Options:
        * -p, --platform
            platform name e.g. hackerrank, codility

    create      create solution class from template and in/out/expected files
      Usage: create [options]
        Options:
        * -c, --classname

          -t, --description

        * -d, --domain

        * -p, --platform
            platform name e.g. hackerrank, codility
        * -s, --subdomain


    list      lists available challenges domains/subdomains
      Usage: list [options]
        Options:
        * -p, --platform
            platform name e.g. hackerrank, codility

    generate      generates all (yet unsolved) solution files from templates
      Usage: generate [options]
        Options:
        * -d, --domain

        * -p, --platform
            platform name e.g. hackerrank, codility
        * -s, --subdomain
```
## Features

- Maven multi-module project
- Libraries
 - utils:           guava, commons-lang3
 - cli:             jcommander
 - template engine: jtwig
- Tech stack: java 8 / maven
- Tools: git flow

*solution-framework* is *TDD* compatibile - it creates *red* tests, you'll have to go *green* yourself.

# License

Licensed under [MIT License](https://opensource.org/licenses/MIT)

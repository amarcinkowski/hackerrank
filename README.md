# solutions framework [![Build Status](https://travis-ci.org/amarcinkowski/solutions-framework.svg?branch=master)](https://travis-ci.org/amarcinkowski/solutions-framework)

Easy usage of IDE (eclipse & IDEA) tools in solving programming challenges.

## Platform support

| feature | ![HackerRank](https://amarcinkowski.github.io/imgs/solutions-framework/hackerrank.png) HackerRank | ![codility](https://amarcinkowski.github.io/imgs/solutions-framework/codility.png) |
|---|---|---|
| list | :heavy_check_mark: | - |
| create | :heavy_check_mark: | :heavy_check_mark: |
| generate | :heavy_check_mark: | - |

### run.sh

```
Usage: run.sh [options] [command] [command options]
  Options:
    -h, --help
      this message
      Default: false
    -v, --version
      version information
      Default: false
  Commands:
    test      null
      Usage: test [options]
        Options:
        * -p, --platform
            platform name e.g. hackerrank, codility

    create      null
      Usage: create [options]
        Options:
        * -c, --classname

          -t, --description

        * -d, --domain

        * -p, --platform
            platform name e.g. hackerrank, codility
        * -s, --subdomain


    list      null
      Usage: list [options]
        Options:
        * -p, --platform
            platform name e.g. hackerrank, codility

    generate      null
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

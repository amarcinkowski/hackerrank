# solutions framework [![Build Status](https://travis-ci.org/amarcinkowski/solutions-framework.svg?branch=master)](https://travis-ci.org/amarcinkowski/solutions-framework)

Easy usage of IDE (eclipse & IDEA) tools in solving programming challenges.

## Platform support

| feature | ![HackerRank](https://amarcinkowski.github.io/imgs/solutions-framework/hackerrank.png) HackerRank | ![codility](https://amarcinkowski.github.io/imgs/solutions-framework/codility.png) |
|---|---|---|
| list | :heavy_check_mark: | - |
| create | :heavy_check_mark: | :heavy_check_mark: |
| generate-all | :heavy_check_mark: | - |

### run.sh

```
 -c,--create <domain,subdomain,class,description>   create solution from template
 -d,--debug                                         be extra verbose
 -g,--generate-all <domain,subdomain>               generate template for all unsolved challenges
 -h,--help                                          print this message
 -l,--list                                          list available domains |- subdomains (slug)
 -q,--quiet                                         be extra quiet
 -r,--run                                           run mvn test
 -u,--unsolved <domain,subdomain>                   list yet unsolved challenges
 -v,--version                                       print the version information and exit
```
## Features

- Maven multi-module project
- Libraries
 - utils:           guava, commons-lang3
 - cli:             jcommander
 - template engine: jtwig
- Tech stack: java 8 / maven
- Tools: git flow

# License

Licensed under [MIT License](https://opensource.org/licenses/MIT)

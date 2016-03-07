# boot-lein

A [boot](https://github.com/boot-clj/boot) plugin designed to help boot generate project.clj.

the initial code comes from

https://github.com/boot-clj/boot/wiki/For-Cursive-Users

## Usage

````clj
(set-env! :dependencies '[[zhuangxm/boot-lein "0.1.1" :scope "test"]])

(require '[zhuangxm.boot-lein :refer [lein]])

````

using command below to generate project.clj

```bash
boot lein
```

## relase history

* 0.1.1  using boot to deploy, change clojure and boot dependency to provided.
* 0.1.0  first version

## License

Copyright © 2016

Distributed under the Eclipse Public License either version 1.0 or (at
your option) any later version.

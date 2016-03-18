(set-env!
  :resource-paths #{"src"}
  :dependencies '[[org.clojure/clojure "1.7.0"     :scope "provided"]
                  [boot/core           "2.5.5"     :scope "provided"]])

(task-options!
  pom {:project     'zhuangxm/boot-lein
       :version     "0.1.2"
       :description "Boot task to generate project.clj file."
       :url         "https://github.com/zhuangxm/boot-lein"
       :scm         {:url "https://github.com/zhuangxm/boot-lein"}
       :license     {"Eclipse Public License"
                     "http://www.eclipse.org/legal/epl-v10.html"}})

(deftask build []
         (comp
           (pom)
           (jar)
           (install)))

(deftask deploy []
         (comp
           (build)
           (push :repo "clojars" :gpg-sign false)))
(ns zhuangxm.boot-lein
  {:boot/export-tasks true}
  (:require [boot.core :refer :all]))

;;original code copy from https://github.com/boot-clj/boot/wiki/For-Cursive-Users
;;add repositroies part

(defn- generate-lein-project-file! [& {:keys [keep-project] :or {:keep-project true}}]
  (require 'clojure.java.io)
  (let [pfile ((resolve 'clojure.java.io/file) "project.clj")
        ; Only works when pom options are set using task-options!
        {:keys [project version description url]} (:task-options (meta #'boot.task.built-in/pom))
        prop #(when-let [x (get-env %2)] [%1 x])
        repos (get-env :repositories)                    ;;drop two default repos
        repos (if (get-env :mirrors) (concat (drop 2 repos) [["mirrors" (-> :mirrors get-env vals first)]])
                                     repos)
        head (list* 'defproject (or project 'boot-project) (or version "0.0.0-SNAPSHOT")
                    (concat
                      [:url url]
                      [:description description]
                      (prop :license :license)
                      [:dependencies (get-env :dependencies)
                       :source-paths (vec (concat (get-env :source-paths)
                                                  (get-env :resource-paths)))
                       :repositories (symbol "^:replace")
                       (vec repos)]))
        proj (boot.util/pp-str head)]
    (if-not keep-project (.deleteOnExit pfile))
    (spit pfile proj)))

(deftask lein
         "Generate a leiningen `project.clj` file.
            This task generates a leiningen `project.clj` file based on the boot
            environment configuration, including project name and version (generated
            if not present), dependencies, and source paths. Additional keys may be added
            to the generated `project.clj` file by specifying a `:lein` key in the boot
            environment whose value is a map of keys-value pairs to add to `project.clj`."
         []
         (generate-lein-project-file! :keep-project true))


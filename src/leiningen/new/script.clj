(ns leiningen.new.script
  (:require [leiningen.new.templates :refer [renderer name-to-path ->files]]
            [leiningen.core.main :as main]))

(def render (renderer "script"))

(defn script
  [name]
  (binding [leiningen.new.templates/*dir* "."]
    (let [data {:name name
                :sanitized (name-to-path name)}]
      (main/info (str (format "Generating stand-alone script \"%s\".  " (:sanitized data))
                      "Don't forget to add {:user {:plugins [[lein-exec \"0.3.1\"]]}} to .lein/profiles.clj."))
      (->files data
               ["{{sanitized}}" (render "template.clj" data) :executable true]))))
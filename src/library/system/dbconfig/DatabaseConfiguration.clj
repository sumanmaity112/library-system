(ns library.system.dbconfig.DatabaseConfiguration
  (:require [clojure.java.jdbc :as jdbc])
  )

(def dbConfig
  {:dbtype   "postgresql"
   :dbname   "library"
   :host     "192.168.33.10"
   :user     "library_admin"
   :password ""
   :classname "org.postgresql.Driver"})
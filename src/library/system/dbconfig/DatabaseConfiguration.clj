(ns library.system.dbconfig.DatabaseConfiguration
  (:require [clojure.java.jdbc :as jdbc]
            [environ.core :refer [env]]
            ))

(def dbConfig
  {:dbtype   "postgresql"
   :dbname   (env :database-name)
   :host     (env :host-name)
   :user     (env :username)
   :password (env :password)
   :classname "org.postgresql.Driver"})
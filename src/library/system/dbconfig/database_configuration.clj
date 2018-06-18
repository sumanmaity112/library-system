(ns library.system.dbconfig.database-configuration
  (:require [environ.core :refer [env]]))

(def db-config
  {:dbtype    (env :dbtype)
   :dbname    (env :database-name)
   :host      (env :host-name)
   :user      (env :username)
   :password  (env :password)
   :classname (env :driver-class-name)})
(ns library.system.utils.db-utils
  (:require [ragtime.repl :as repl]
            [ragtime.jdbc :as jdbc]
            [library.system.dbconfig.database-configuration :refer [db-config]]))


(defn ^:private load-config []
  {:datastore  (jdbc/sql-database db-config)
   :migrations (jdbc/load-resources "migrations")})

(defn migrate []
  (repl/migrate (load-config)))

(defn rollback []
  (repl/rollback (load-config)))



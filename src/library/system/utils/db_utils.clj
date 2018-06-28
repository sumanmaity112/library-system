(ns library.system.utils.db-utils
  (:require [ragtime.repl :as repl]
            [ragtime.jdbc :as jdbc]
            [library.system.dbconfig.database-configuration :refer [db-config]]))

(defn ^:private get-migrations []
  (jdbc/load-resources "migrations"))

(defn ^:private load-config []
  {:datastore  (jdbc/sql-database db-config)
   :migrations (get-migrations)})

(defn migrate []
  "Migrate the datastore up to the latest migration"
  (repl/migrate (load-config)))

(defn rollback []
  "Rollback one migrations from the datastore."
  (repl/rollback (load-config)))

(defn rollback [last-n]
  "Rollback last n migrations from the datastore."
  (repl/rollback (load-config) last-n))

(defn rollback-all []
  "Rollback all migrations run on the datastore"
  (rollback (count (get-migrations))))



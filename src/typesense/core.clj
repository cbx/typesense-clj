(ns typesense.core
  (:require [typesense.utils :as utils]
            [typesense.api :as api]
            [clj-http.client :as client]))

(defn create-collection
  "Create collection using the supplied collection schema."
  [settings schema]
  (let [req (api/create-collection-req settings schema)]
    (utils/handle-json-response (client/post (:uri req) (:req req)))))

(defn drop-collection
  "Permanently drops a collection. This action cannot be undone.
  For large collections, this might have an impact on read latencies."
  [settings collection-name]
  (let [req (api/drop-collection-req settings collection-name)]
    (utils/handle-json-response (client/delete (:uri req) (:req req)))))

(defn list-collections
  "Returns a summary of all your collections.
  The collections are returned sorted by creation date,
  with the most recent collections appearing first."
  [settings]
  (let [req (api/list-collections-req settings)]
    (utils/handle-json-response (client/get (:uri req) (:req req)))))

(defn retrieve-collection
  "Retrieves collection on collection name."
  [settings collection-name]
  (let [req (api/retrieve-collection-req settings collection-name)]
    (utils/handle-json-response (client/get (:uri req) (:req req)))))

(defn create-document
  "Indexes the document."
  [settings collection-name document]
  (let [req (api/create-document-req settings collection-name document)]
    (utils/handle-json-response (client/post (:uri req) (:req req)))))

(defn upsert-document
  "Indexes the document."
  [settings collection-name document]
  (let [req (api/upsert-document-req settings collection-name document)]
    (utils/handle-json-response (client/post (:uri req) (:req req)))))

(defn retrieve-document
  "Retrieves the document on id in the specified collection."
  [settings collection-name id]
  (let [req (api/retrieve-document-req settings collection-name id)]
    (utils/handle-json-response (client/get (:uri req) (:req req)))))

(defn delete-document
  "Deletes the document on id in the specified collection."
  [settings collection-name id]
  (let [req (api/delete-document-req settings collection-name id)]
    (utils/handle-json-response (client/delete (:uri req) (:req req)))))

(defn update-document
  "Update an individual document from a collection by using its id.
  The update can be partial"
  [settings collection-name id document]
  (let [req (api/update-document-req settings collection-name id document)]
    (utils/handle-json-response (client/patch (:uri req) (:req req)))))

(defn import-documents
  "Imports documents in the specified collection."
  ([settings collection-name documents]
   (import-documents settings collection-name documents {}))
  ([settings collection-name documents parameters]
   (let [req (api/import-documents-req settings collection-name documents parameters)]
     (utils/handle-jsonline-response (client/post (:uri req) (:req req))))))

(defn delete-documents
  "Delete documents."
  [settings collection-name parameters]
  (let [req (api/delete-documents-req settings collection-name parameters)]
    (utils/handle-json-response (client/delete (:uri req) (:req req)))))

(defn export-documents
  "Exports documents in the specified collection."
  [settings collection-name parameters]
  (let [req (api/export-documents-req settings collection-name parameters)]
    (utils/handle-jsonline-response (client/get (:uri req) (:req req)))))

(defn search
  "Search for documents using the specified query parameters."
  [settings collection-name parameters]
  (let [req (api/search-req settings collection-name parameters)]
    (utils/handle-jsonline-response (client/get (:uri req) (:req req)))))

(ns blog.post
  (:require [hiccup.page :refer [html5 include-css]]
            [blog.layout :refer [with-navbar]]))

(defn render [{global-meta :meta posts :entries post :entry :as opts}]
  (with-navbar opts
    [:h1 (:title post)]
    [:div.blog-post (:content post)]))

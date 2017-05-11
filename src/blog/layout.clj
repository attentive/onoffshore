(ns blog.layout
  (:require [hiccup.page :refer [html5 include-css]]))

(defn with-navbar [{global-meta :meta posts :entries} & contents]
  (html5 {:lang "en" :itemtype "http://schema.org/Blog"}
         [:head
          [:meta {:charset "utf-8"}]
          [:meta {:content "width=device-width, initial-scale=1, shrink-to-fit=no", :name "viewport"}]
          [:meta {:content (:description global-meta), :name "description"}]
          [:meta {:content (:author global-meta), :name "author"}]
          [:link {:href "/favicon.ico", :rel "icon"}]
          [:title (:site-title global-meta)]
          [:link {:href "//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/css/bootstrap.min.css", :rel "stylesheet"}]
          [:script {:src "//code.jquery.com/jquery-3.1.1.slim.min.js"}]
          [:script {:src "//cdnjs.cloudflare.com/ajax/libs/tether/1.4.0/js/tether.min.js"}]
          [:script {:src "//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-alpha.6/js/bootstrap.min.js"}]
          [:style ".top-spacer { margin-top: 100px; }"]]
         ;[:link {:rel "stylesheet", :href "css/tomlynch.css"}]
         ;[:link {:rel "stylesheet", :href "starter-template.css"}]]
         [:body
          [:nav.navbar.navbar-toggleable-md.navbar-inverse.bg-inverse.fixed-top
           [:button.navbar-toggler.navbar-toggler-right
            {:aria-label "Toggle navigation",
             :aria-expanded "false",
             :aria-controls "the-navbar",
             :data-target "#the-navbar",
             :data-toggle "collapse",
             :type "button"}
            [:span.navbar-toggler-icon]]
           [:a.navbar-brand {:href "#"} (:site-title global-meta)]
           [:div#the-navbar.collapse.navbar-collapse
            [:ul.navbar-nav.mr-auto
             [:li.nav-item.active
              [:a.nav-link {:href "#"} "Home " [:span.sr-only "(current)"]]]
             [:li.nav-item [:a.nav-link {:href "#"} "Link"]]
             [:li.nav-item [:a.nav-link.disabled {:href "#"} "Disabled"]]
             [:li.nav-item.dropdown
              [:a#dropdown01.nav-link.dropdown-toggle
               {:aria-expanded "false",
                :aria-haspopup "true",
                :data-toggle "dropdown",
                :href "http://example.com"}
               "Dropdown"]
              [:div.dropdown-menu
               {:aria-labelledby "dropdown01"}
               [:a.dropdown-item {:href "#"} "Action"]
               [:a.dropdown-item {:href "#"} "Another action"]
               [:a.dropdown-item {:href "#"} "Something else here"]]]]
            [:form.form-inline.my-2.my-lg-0
             [:input.form-control.mr-sm-2
              {:placeholder "Search", :type "text"}]
             [:button.btn.btn-outline-success.my-2.my-sm-0
              {:type "submit"}
              "Search"]]]]
             (into [] `(:div.container.top-spacer ~@contents))]))


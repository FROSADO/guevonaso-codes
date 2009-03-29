__author__="guevonaso"
__date__ ="$24-mar-2009 13:53:46$"
import os
from google.appengine.ext import webapp
from google.appengine.api import users
from google.appengine.ext.webapp import template
from google.appengine.ext.webapp.util import run_wsgi_app

class MainPage(webapp.RequestHandler):
    def get(self):
        
        user = users.get_current_user();
        url_login = users.create_login_url(self.request.uri)
        url_logout = users.create_logout_url(self.request.uri)
        path = os.path.join(os.path.dirname(__file__), 'templates/sample.html')
        template_values = {'user':user,
                           'url_login':url_login,
                           'url_logout':url_logout
                           }
        self.response.out.write(template.render(path, template_values))



        
application = webapp.WSGIApplication ([('/',MainPage)]
                                      ,debug=True)
def main():
    run_wsgi_app(application);

if __name__ == "__main__":
    main()
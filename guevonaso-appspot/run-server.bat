SET THIS=%~dp0
SET GOOGLE=C:\tools\google_appengine
SET SRC=%THIS%src
cd %GOOGLE%
python %GOOGLE%\dev_appserver.py %SRC%
cd %THIS%
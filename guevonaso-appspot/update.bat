SET THIS=%~dp0
SET GOOGLE=C:\tools\google_appengine
SET SRC=%THIS%\src
cd %GOOGLE%
python %GOOGLE%\appcfg.py update %SRC%
cd %THIS%
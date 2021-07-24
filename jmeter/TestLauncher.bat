@echo off
for /f "tokens=2 delims==" %%G in ('wmic os get localdatetime /value') do set datetime=%%G

set year=%datetime:~0,4%
set month=%datetime:~4,2%
set day=%datetime:~6,2%

set dt=_H%TIME:~0,2%_M%TIME:~3,2%_S%TIME:~6,2%
set dt=%dt: =0%

jmeter.bat -n -t PostUserGenerateTokenAuthorized.jmx -l TestResult-%year%-%month%-%day%_%dt%.csv -e -o HTMLReport-%year%-%month%-%day%_%dt%
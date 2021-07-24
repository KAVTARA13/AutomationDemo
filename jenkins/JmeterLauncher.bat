@echo off
for /f "tokens=2 delims==" %%G in ('wmic os get localdatetime /value') do set datetime=%%G

set year=%datetime:~0,4%
set month=%datetime:~4,2%
set day=%datetime:~6,2%

set dt=_H%TIME:~0,2%_M%TIME:~3,2%_S%TIME:~6,2%
set dt=%dt: =0%

jmeter.bat -n -t jmeter/PostUserGenerateTokenAuthorized.jmx -l allure-report-final/JMeter-csv/Jmeter-TestResult-%year%-%month%-%day%_%dt%.csv -e -f -o allure-report-final/JMeter-HTMLReport -jmeter.reportgenerator.overall_granularity=5000
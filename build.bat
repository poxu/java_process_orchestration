if not exist build mkdir build
cd build
echo "compiling java"
dir /s /B ..\src\main\*.java > sources.txt
javac -d graalout @sources.txt
echo "compiling native"

call native-image ^
--static -cp graalout com.evilcorp.executable.DataProducer producer
echo "removing window generation script"
rem editbin /SUBSYSTEM:WINDOWS producer.exe
echo over

xcopy /Y producer.exe ..
call native-image ^
--static -cp graalout com.evilcorp.executable.DataConsumer consumer
echo over

xcopy /Y consumer.exe ..
call native-image ^
--static -cp graalout com.evilcorp.orchestrator.ProcessCombiner combiner
echo over
xcopy /Y combiner.exe ..

cd ..


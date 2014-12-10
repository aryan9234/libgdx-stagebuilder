releaseVersion=$1
cd ..
mvn versions:set -DnewVersion=$releaseVersion
if [ $? -ne 0 ]; then
    echo "Maven versioning operation failed!"
    exit 1
fi
find . -name "pom.xml.versionsBackup" -delete
echo "Parent and child poms updated with version : $releaseVersion"
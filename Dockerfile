FROM budtmo/docker-android-x86-8.1

# Install Maven
RUN apt-get update && \
    apt-get install -y maven

# Copy the APK file
COPY sample_apk/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk  /app/Android.SauceLabs.Mobile.Sample.app.2.7.1.apk

#Copy files
COPY src  /app/src
COPY pom.xml  /app/pom.xml

# Expose necessary ports
EXPOSE 6080 4723 5554 5555

# Set environment variables
ENV DEVICE="Samsung Galaxy S6" \
    APPIUM=true \
    CONNECT_TO_GRID=true \
    APPIUM_HOST="127.0.0.1" \
    APPIUM_PORT=4723 \
    SELENIUM_HOST="172.17.0.1" \
    SELENIUM_PORT=4444 \
    MOBILE_WEB_TEST=true
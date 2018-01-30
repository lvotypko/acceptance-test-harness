FROM jenkins/java:3fedc9a20524
COPY *.pub /tmp
RUN cat /tmp/*.pub >> /home/test/.ssh/authorized_keys

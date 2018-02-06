FROM jenkins/java:5c2535ccfddd
COPY *.pub /tmp
RUN cat /tmp/*.pub >> /home/test/.ssh/authorized_keys

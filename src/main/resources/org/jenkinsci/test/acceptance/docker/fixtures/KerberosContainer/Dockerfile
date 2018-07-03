FROM centos:7
MAINTAINER ogondza@gmail.com

ARG HOST_NAME


RUN yum -y install\
    krb5-libs\
    krb5-server\
    krb5-workstation

ADD src/setIp.sh /tmp/hostname_export.sh
#RUN cat /tmp/hostname_export.sh
#RUN sh /tmp/hostname_export.sh
#RUN cat /tmp/hostname_export.sh >> ~/.bashrc
#RUN cat /tmp/hostname_export.sh >> /etc/profile
#RUN hostname
#RUN echo $ADDRESS
#RUN echo hello


ADD src/etc.krb5.conf /etc/krb5.conf
ADD src/var.kerberos.krb5kdc.kadm5.acl /var/kerberos/krb5kdc/kadm5.acl
ADD src/keytab.sh keytab.sh


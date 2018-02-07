FROM jenkins/ssh-agent:951780fb3f84

RUN ls /etc |grep sudo
RUN cat /etc/sudoers
RUN usermod -aG sudo test
RUN cat /etc/sudoers

FROM       centos
MAINTAINER ronry "miletter@163.com"

RUN yum install -y openssh-server

RUN        ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key
RUN        ssh-keygen -t dsa -f /etc/ssh/ssh_host_dsa_key

RUN echo 'root:miletter' |chpasswd

RUN sed -ri 's/^PermitRootLogin\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config

EXPOSE 22
EXPOSE 80

CMD    ["/usr/sbin/sshd", "-D"]


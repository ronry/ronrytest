FROM       centos
MAINTAINER ronry "miletter@163.com"

RUN yum install -y java-1.8.0-openjdk.x86_64

RUN yum install -y openssh-server
#RUN        ssh-keygen -t rsa -f /etc/ssh/ssh_host_rsa_key
#RUN        ssh-keygen -t ecdsa -f /etc/ssh/ssh_host_ecdsa_key
COPY ssh/* /etc/ssh/
RUN sed -ri 's/^PermitRootLogin\s+.*/PermitRootLogin yes/' /etc/ssh/sshd_config
RUN sed -ri 's/UsePAM yes/#UsePAM yes/g' /etc/ssh/sshd_config

EXPOSE 22
EXPOSE 80

## 在有ENTRYPOINT指令的情况下，下面两个都将会是ENTRYPOINT的参数而已
#CMD /usr/sbin/sshd -D
#CMD ["/usr/sbin/sshd", "-D"]

RUN echo 'root:miletter' | chpasswd

COPY apache-tomcat-9.0.0.M9.tar.gz /usr/local/share
RUN cd /usr/local/share && tar -zxvf apache-tomcat-9.0.0.M9.tar.gz

COPY start.sh /root
## /bin/sh -c /root/start.sh
ENTRYPOINT /root/start.sh

Open Service Broker API

[Open Service Broker API官网](https://www.openservicebrokerapi.org/)  
[Open Service Broker API Github](https://github.com/openservicebrokerapi/servicebroker)  

Open Service Broker API定义了平台和Service Broker之间的HTTP（S）接口。

The Open Service Broker API project allows independent software vendors, SaaS providers and developers to easily provide backing services to workloads running on cloud native platforms such as Cloud Foundry and Kubernetes. The specification, which has been adopted by many platforms and thousands of service providers, describes a simple set of API endpoints which can be used to provision, gain access to and managing service offerings.

Open Service Broker API项目使独立软件供应商，SaaS提供者和开发人员可以轻松地为运行在Cloud Foundry和Kubernetes等云原生平台上的工作负载提供支持服务。 该规范已被许多平台和数千家服务提供商采用，它描述了一组简单的API端点，可用于提供，获取和管理服务产品。



The Open Service Broker API defines an HTTP(S) interface between Platforms and Service Brokers.

Open Service Broker API定义了平台和Service Broker之间的HTTP（S）接口。

The Service Broker is the component of the service that implements the Service Broker API, for which a Platform is a client. Service Brokers are responsible for advertising a catalog of Service Offerings and Service Plans to the Platform, and acting on requests from the Platform for provisioning, binding, unbinding, and deprovisioning.

Service Broker是实现Service Broker API的服务的组件，平台是其客户端。服务经纪人负责向平台发布服务产品和服务计划的目录，并根据平台的要求进行调配，绑定，解除绑定和取消置备。

In general, provisioning reserves a resource on a service; we call this reserved resource a Service Instance. What a Service Instance represents can vary by service. Examples include a single database on a multi-tenant server, a dedicated cluster, or an account on a web application.

通常，供应会在服务上保留资源；我们将此保留资源称为服务实例。服务实例表示的内容可能因服务而异。示例包括多租户服务器上的单个数据库，专用群集或Web应用程序上的帐户。

What a Service Binding represents MAY also vary by service. In general, creation of a Service Binding either generates credentials necessary for accessing the resource or provides the Service Instance with information for a configuration change.

服务绑定表示的内容可能因服务而异。通常，服务绑定的创建要么生成访问资源所必需的凭据，要么为服务实例提供配置更改的信息。

A Platform MAY expose services from one or many Service Brokers, and an individual Service Broker MAY support one or many Platforms using different URL prefixes and credentials.

平台可以公开来自一个或多个Service Broker的服务，而单个Service Broker可以使用不同的URL前缀和凭据来支持一个或多个Platform。



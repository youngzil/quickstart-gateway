https://github.com/alibaba/Sentinel/wiki/%E4%BB%8B%E7%BB%8D


zuul2集成Zuul2 integration 
https://github.com/alibaba/Sentinel/pull/1138


比如Zuul2流控
1、ContextUtil.enter，最后finally中执行ContextUtil.exit()
2、SentinelZuulInboundFilter中把限流Deque<EntryHolder>保存到上线文Context中
3、流控就直接调用降级服务SentinelZuulEndpoint
4、SentinelZuulOutboundFilter中把限流Deque<EntryHolder>循环执行exit

详细流程：
CtSph.asyncEntryWithPriorityInternal返回AsyncEntry，使用new EntryHolder(entry, params)包装后放入Deque<EntryHolder>，在保存到上下文Context
1、加载ProcessorSlotChain：lookProcessChain：
	a、SPI加载SlotChainBuilder SpiLoader.loadFirstInstanceOrDefault(SlotChainBuilder.class, DefaultSlotChainBuilder.class);
	b、没有配置，就使用的是默认的DefaultSlotChainBuilder
	c、使用DefaultProcessorSlotChain串联起来一个链表结构的责任链，zuul2中使用的是数组，因为有filterOrder用来定义顺序，更灵活
	d、ProcessorSlot都有entry和exit方法，以及往后链式调用next的fireEntry和fireExit方法
	e、节点NodeSelectorSlot
	   集群ClusterBuilderSlot
	   日志LogSlot
	   统计StatisticSlot
	   权限AuthoritySlot
	   系统SystemSlot：qps、线程数等
	   流控FlowSlot
	   降级DegradeSlot
2、执行ProcessorSlotChain
3、初始化syncEntry.initAsyncContext();
4、清理asyncEntry.cleanCurrentEntryInLocal();



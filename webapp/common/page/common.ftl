<#include "page.ftl"/>

<#macro StringHelper str maxlength=40>
	<#if str ??>
		<#if (str?length <= maxlength)>
			${str!''}
		<#else>
			${str?substring(maxlength)}...
		</#if>
	</#if>
</#macro>
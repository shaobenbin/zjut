<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<style>
.app_title {
	padding-left: 15px;
}
.app_title li {
	display: inline-block;
	*display: inline;
	*zoom: 1;
	/*float: left;*/
	background: #e69e1a;
	border-radius: 5px 5px 0 0;
	color: #616161;
	cursor: pointer;
}
.app_title li:hover {
	background: #e97900;
}
.app_title li.active {
	background: #e97900;
	color: #fff;
	padding: 10px 25px 0 0 ;
}
.app_title h2 {
	display: inline-block;
	width: 180px;
	text-align: center;
	font-size: 14px;
	padding: 3px 0 5px 25px;
	background: url(img/app_title_bg.png) 25px center no-repeat;
}
.applicationform form {
	font-size: 12px;
	color: #e97900;
	display: none;
}
.applicationform form.active {
	display: block;
}
.applicationform fieldset {
	border: 1px solid #e97900;
	border-top: none;
	border-bottom: none;
}
.applicationform .bordertop {
	border: 1px solid #e97900;
	border-bottom: none;
	border-radius: 5px 5px 0 0;
}
.applicationform .borderbot {
	border: 1px solid #e97900;
	border-top: none;
	border-radius: 0 0 5px 5px;
	margin-bottom: 15px;
}
.applicationform form sup {
	color: red;
}
.applicationform form label {
	margin-bottom: 6px;
	display: inline-block;
	*position: relative;
	*top: 6px;
}
 .applicationform form h4 {
 	margin-bottom: 6px;
 }
 .applicationform form input {
 	padding: 5px 6px;
 	height: 13px;
 	line-height: 1;
 }
  .applicationform form select {
  	padding: 2px 6px 3px;
  	width: 160px;
  }
.applicationform h3 {
	height: 37px;
	width: 100%;
	line-height: 37px;
	text-indent: 30px;
	color: #fff;
	font-size: 14px;
	background: #a35615 url(img/legend_bg.png) 15px center no-repeat;
	margin-bottom: 10px;
}
.applicationform table {
	width: 910px;
	margin: 0 auto;
}
.applicationform table .labeltd td {
	padding-bottom: 0px;
}
.applicationform td {
	padding: 15px 0;
}
.applicationform .underline td {
	border-bottom: 1px dashed #b0aead;
}
.applicationform .underline td table td {
	border: none;
}
.applicationform .underline td table td label {
	line-height: 1.3;
	padding-right: 5px;
}

.applicationform .btns {
	text-align: center;
	border: none;
}
.applicationform .btns input {
	width: 148px;
	height: 39px;
	text-indent: -9999px;
	background: none;
	border: none;
	cursor: pointer;
}
.applicationform .btns .subbtn {
	background: url(img/submitbtn.png);
	margin-right: 10px;
}
.applicationform .btns .resetbtn {
	background: url(img/resetbtn.png);
}

#ui-datepicker-div {
	font-size: 12px;
}
.ui-datepicker-trigger {
	cursor: pointer;
	margin-left: 6px;
}

.baidumap {
	height: 500px;
	background: #eee;
}

.changemap {
	padding-top: 15px;
	text-align: center;
	font-size: 16px;
}
.changemap button {
	width: 128px;
	height: 36px;
	cursor: pointer;
	margin-right: 15px;
	border: 1px solid #dda43d;
	border-radius: 5px;
	text-shadow: 1px 1px #fff;
}

.addressmessage td {
	text-align: right;
	padding: 5px 0;
}
.addressmessage td h4 {
	text-align: left;
}
.addressmessage td label {
	margin-right: 10px;
}


.memo {
	padding: 13px 18px 30px;
	overflow: hidden;
	zoom: 1;
}
.memo textarea {
	resize: none;
	padding: 5px 6px;
	float: left;
}
.memo dl {
	float: left;
	vertical-align: top;
	padding: 8px 0 0 30px ;
}
.memo dt, .memo dd {
	margin: 0 0 15px 0;
}

.permanentaddress td {
	padding: 5px 0;
	text-align: right;
}
.permanentaddress td label {
	margin-right: 10px;
}
</style>
</head>
<body class="page1">
	<h2>Application form for <br />language program</h2>
	<h3>基础信息</h3>
	<fieldset class="bordertop">
	<table>
		<tr class="underline">
			<td>
				<div>
					<label for="familyname2">护照姓名 Family Name <sup>*</sup></label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(familyname)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="firstname2">First Name <sup>*</sup></label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(firstname)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="middlename2">Middle Name</label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(middlename)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="chinesename2">中文姓名 Chinese Name</label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(chinesename)!''}</div>
				</div>
			</td>
		</tr>
		<tr class="underline">
			<td colspan="4">
				<div>
					<h4>性别 Gender <sup>*</sup></h4>
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(gender)!''}</div>
				</div>
			</td>
		</tr>
		<tr class="underline">
			<td>
				<div>
					<label for="dateofbirth2">出生日期 Date of Birth(Y-M-D) <sup>*</sup></label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(dateofbirth)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="nationally2">国籍 Nationally <sup>*</sup></label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(nationally)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="countryofbirth2">出生地点 Country of Birth <sup>*</sup></label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(countryofbirth)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="passportno2">护照号码 Passport No. <sup>*</sup></label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(passportno)!''}</div>
				</div>
			</td>
		</tr>
		<tr class="underline">
			<td>
				<div>
					<h4>婚姻情况 Marital Status</h4>
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(marital)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="religion2">宗教信仰 Religion</label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(religion)!''}</div>
				</div>
			</td>
			<td colspan="2">
				<div>
					<label for="occupationorstatus2">职业或身份 Occupation or Status</label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(occupationorstatus)!''}</div>
				</div>
			</td>
		</tr>
		<tr>
			<td>
				<div>
					<label for="emailaddress2">电子邮件 E-mail Address <sup>*</sup></label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(emailaddress)!''}</div>
				</div>
			</td>
			<td colspan="3">
				<div>
					<label for="employer2">工作或学习单位 Employer or School Affiliated</label><br />
					<div style ="width:280px;height:26px;border:solid 1px grey;">${(employer)!''}</div>
				</div>
			</td>
		</tr>
	</table>
	</fieldset>
	<fieldset style="padding-bottom:15px;">
		<h3>联系地址 Permanent Address</h3>
		<table class="permanentaddress">
			<tr>
				<td colspan="2">
					<label for="perstreet">街道 STREET</label><div style ="width:439px;height:26px;border:solid 1px grey;">${(perstreet)!''}</div>
				</td>
				<td>
					<label for="percity">城市 City</label><div style ="width:164px;height:26px;border:solid 1px grey;">${(percity)!''}</div>
				</td>
			</tr>
			<tr class="underline" style="padding:5px 0">
				<td>
					<label for="percountry">国家 Country</label><div style ="width:164px;height:26px;border:solid 1px grey;">${(percountry)!''}</div>
				</td>
				<td>
					<label for="perzipcode">邮编 Zipcode</label><div style ="width:164px;height:26px;border:solid 1px grey;">${(perzipcode)!''}</div>
				</td>
				<td>
					<label for="perphonenumber">电话号码 Phone Number</label><div style ="width:164px;height:26px;border:solid 1px grey;">${(perphonenumber)!''}</div>
				</td>
			</tr>
			<tr>
				<td colspan="2">
					<label for="permanentemail">E-mail</label>
					<div style ="width:440px;height:26px;border:solid 1px grey;">${(permanentemail)!''}</div>
				</td>
				<td>
					<label for="faxbumber">传真号码  Fax Number</label>
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(faxbumber)!''}</div>
				</td>
			</tr>
		</table>
		</fieldset>
		<fieldset class="borderbot">
			<h3>备注信息 Memo</h3>
			<table>
				<tr>
					<td>
						<label for="degreeobtained">最后学历 Highest Academic Degree Obtained</label>
						<div style ="width:440px;height:26px;border:solid 1px grey;">${(degreeobtained)!''}</div>
					</td>
				</tr>
				<tr>
					<td>
						<label for="learnedchinese">何时何地学过汉语  Duration and Place of Previous Chinese Study</label>
						<div style ="width:440px;height:26px;border:solid 1px grey;">${(learnedchinese)!''}</div>
					</td>
				</tr>
				<tr>
					<td>
						现有汉语水平  Present Level of Chinese<div style ="width:440px;height:26px;border:solid 1px grey;">${(levelofchinese)!''}</div>
					</td>
				</tr>
				<tr class="underline">
					<td>
						<span>在浙江工业大学学习时间<br /> Expected Duration at Zhejiang University of Technology</span>
						<label for="inzjutstart" style="margin-left:25px;">从 From</label>
						<span style ="width:164px;height:26px;border:solid 1px grey;">${(inzjutstart)!''}</span>
						<label for="inzustend" style="margin-left:25px;">至 To</label>
						<span style ="width:164px;height:26px;border:solid 1px grey;">${(inzustend)!''}</span>
					</td>
				</tr>
				<tr class="labeltd">
					<td>
						<div>经费来源  Source of Funding</div>
					</td>
				</tr>
				<tr>
					<td>
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(sourceoffunding)!''}</div>
					</td>
				</tr>
			</table>
		</fieldset>
</body>
</html>
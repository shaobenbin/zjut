<h2>Application form for <br />degree</h2>
<fieldset class="bordertop">
	<h3>基础信息</h3>
	<table>
		<tr class="underline">
			<td>
				<div>
					<label for="familyname">护照姓名 Family Name <sup>*</sup></label>
					<br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(familyname)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="firstname">First Name <sup>*</sup></label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(firstname)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="middlename">Middle Name</label><br />
					<div style ="width:164px;height:26px;border:solid 1px grey;">${(middlename)!''}</div>
				</div>
			</td>
			<td>
				<div>
					<label for="chinesename">中文姓名 Chinese Name</label><br />
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
						<label for="dateofbirth">出生日期 Date of Birth(Y-M-D) <sup>*</sup></label>
						<br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(dateofbirth)!''}</div>
					</div>
				</td>
				<td>
					<div>
						<label for="nationally">国籍 Nationally <sup>*</sup></label><br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(nationally)!''}</div>
					</div>
				</td>
				<td>
					<div>
						<label for="countryofbirth">出生地点 Country of Birth <sup>*</sup></label>
						<br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(countryofbirth)!''}</div>
					</div>
				</td>
				<td>
					<div>
						<label for="passportno">护照号码 Passport No. <sup>*</sup></label><br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(passportno)!''}</div>
					</div>
				</td>
			</tr>
			<tr class="underline">
				<td>
					<div>
						<h4>婚姻情况 Marital Status</h4>
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(married)!''}</div>
					</div>
				</td>
				<td>
					<div>
						<label for="religion">宗教信仰 Religion</label><br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(religion)!''}</div>
					</div>
				</td>
				<td colspan="2">
					<div>
						<label for="occupationorstatus">职业或身份 Occupation or Status</label><br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(occupationorstatus)!''}</div>
					</div>
				</td>
			</tr>
			<tr class="underline">
				<td>
					<div>
						<label for="emailaddress">电子邮件 E-mail Address <sup>*</sup></label><br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(emailaddress)!''}</div>
					</div>
				</td>
				<td colspan="3">
					<div>
						<label for="employer">工作或学习单位 Employer or School Affiliated</label><br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(employer)!''}</div>
					</div>
				</td>
			</tr>
			<tr class="labeltd">
				<td colspan="4">
					<div>在华事务联系人 The Guarantors Charging Your Case in China</div>
				</td>
			</tr>
			<tr>
				<td>
					<div>
						<label for="guarantorsname">姓名 Name</label><br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(guarantorsname)!''}</div>
					</div>
				</td>
				<td colspan="3">
					<div>
						<label for="guarantorstel">电话 Tel</label><br />
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(guarantorstel)!''}</div>
					</div>
				</td>
			</tr>
		</table>
		</fieldset>
		<fieldset style="padding-bottom:15px;">
			<h3>住址信息 Address Message</h3>
			<table class="addressmessage">
				<tr>
					<td colspan="2">
						<h4>本国地址 PERMANENT HOME ADDRESS AND TELEPHONE NO. <sup>*</sup></h4>
						<label for="perstreet">街道 STREET</label>
						<div style ="width:439px;height:26px;border:solid 1px grey;">${(perstreet)!''}</div>
					</td>
					<td>
						<h4>&nbsp;</h4>
						<label for="percity">城市 City</label>
						<div style ="width:164px;height:26px;border:solid 1px grey;">${(percity)!''}</div>
					</td>
				</tr>
				<tr class="underline" style="padding:5px 0">
					<td><label for="percountry">国家 Country</label><div style ="width:164px;height:26px;border:solid 1px grey;">${(percountry)!''}</div></td>
					<td><label for="perzipcode">邮编 Zipcode</label><div style ="width:164px;height:26px;border:solid 1px grey;">${(perzipcode)!''}</div></td>
					<td><label for="perphonenumber">电话号码 Phone Number</label><div style ="width:164px;height:26px;border:solid 1px grey;">${(perphonenumber)!''}</div></td>
				</tr>
				<tr>
					<td colspan="3" style="text-align: left">
						<label for="permail">邮寄地址(若与上述地址不同) Mailing Address(if different)</label>
						<div style ="width:450px;height:26px;border:solid 1px grey;">${(permail)!''}</div>
					</td>
				</tr>
			</table>
			</fieldset>
			<fieldset style="padding-bottom:20px">
				<h3>专业申请 Applying Major</h3>
				<table class="applymajor">
					<tr class="underline">
						<td colspan="3">
							<label for="applyingas">申请类别 Applying As <sup>*</sup></label>
							<div style ="width:450px;height:26px;border:solid 1px grey;">${(applyingas)!''}</div>
						</td>
					</tr>
					<tr class="labeltd">
						<td colspan="3">
							<div>来浙江工业大学学习的专业 Intended major or field of study <sup>*</sup></div>
						</td>
					</tr>
					<tr class="underline">
						<td>
							<label for="majorfchoice">Then first choice</label>
							<div style ="width:164px;height:26px;border:solid 1px grey;">${(majorfchoice1)!''}</div>
						</td>
						<td>
							<label for="majorschoice">Then second choice</label>
							<div style ="width:164px;height:26px;border:solid 1px grey;">${(majorschoice2)!''}</div>
						</td>
						<td>
							<label for="majortchoice">Then third choice</label>
							<div style ="width:164px;height:26px;border:solid 1px grey;">${(majortchoice3)!''}</div>
						</td>
					</tr>
					<tr class="underline">
						<td colspan="3">
							留学期限 Enrollment<sup>*</sup>
							<label for="enrollmentform" style="margin: 0 5px 0 25px">从 From</label>
							<span style ="width:164px;height:26px;border:solid 1px grey;">${(enrollmentform)!''}</span>
							<label for="enrollmentto" style="margin: 0 5px 0 25px">至 To</label>
							<span style ="width:164px;height:26px;border:solid 1px grey;">${(enrollmentto)!''}</span>
						</td>
					</tr>
					<tr class="underline">
						<td colspan="3">
							<label for="applyobtained">最后学历 Highest Academic Degree Obtained</label>
							<div style ="width:164px;height:26px;border:solid 1px grey;">${(applyobtained)!''}</div>
						</td>
					</tr>
					<tr class="underline" >
						<td colspan="3">
							<h4>学习经历 Education Background</h4>
							<table>
								<tr>
									<td>
										<label for="schoolname1">学校名称<br /> Name of School / College / University</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(schoolname1)!''}</div>
									</td>
									<td>
										<label for="citycountry1">城市/国家<br /> City/Country</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(citycountry1)!''}</div>
									</td>
									<td>
										<label for="learnstart1">开始时间(月/年) <br /> From:(Mo/Yr)</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(learnstart1)!''}</div>
									</td>
									<td>
										<label for="learnend1">结束时间(月/年) <br /> To:(Mo/Yr)</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(learnend1)!''}</div>
									</td>
									<td>
										<label for="degreeearned1">所获学位/年份 <br /> Degree Earned/Year</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(degreeearned1)!''}</div>
									</td>
								</tr>
								<tr>
									<td>
										<label for="schoolname2">学校名称<br /> Name of School / College / University</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(schoolname2)!''}</div>
									</td>
									<td>
										<label for="citycountry2">城市/国家<br /> City/Country</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(citycountry2)!''}</div>
									</td>
									<td>
										<label for="learnstart2">开始时间(月/年) <br /> From:(Mo/Yr)</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(learnstart2)!''}</div>
									</td>
									<td>
										<label for="learnend2">结束时间(月/年) <br /> To:(Mo/Yr)</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(learnend2)!''}</div>
									</td>
									<td>
										<label for="degreeearned2">所获学位/年份 <br /> Degree Earned/Year</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(degreeearned2)!''}</div>
									</td>
								</tr>
								<tr>
									<td>
										<label for="schoolname3">学校名称<br /> Name of School / College / University</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(schoolname3)!''}</div>
									</td>
									<td>
										<label for="citycountry3">城市/国家<br /> City/Country</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(citycountry3)!''}</div>
									</td>
									<td>
										<label for="learnstart3">开始时间(月/年) <br /> From:(Mo/Yr)</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(learnstart3)!''}</div>
									</td>
									<td>
										<label for="learnend3">结束时间(月/年) <br /> To:(Mo/Yr)</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(learnend3)!''}</div>
									</td>
									<td>
										<label for="degreeearned3">所获学位/年份 <br /> Degree Earned/Year</label><br />
										<div style ="width:164px;height:26px;border:solid 1px grey;">${(degreeearned3)!''}</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</fieldset>
			<fieldset class="borderbot" >
				<h3>备注 Memo</h3>
				<div class="memo">
					<textarea name="appDegreeBean.memotext" id="memotext" cols="50" rows="12">${(memotext)!''}</textarea>
					<dl>
						<dt>Applicant should submit the following documents with the application form:</dt>
						<dd>（1）最后学历证书 Highest Educational Diploma</dd>
						<dd>（2）学习成绩单 Transcript Related</dd>
						<dd>（3）护照复印件 Photocopy of Passport</dd>
					</dl>
				</div>
			</fieldset>
			
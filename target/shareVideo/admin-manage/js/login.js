$(function() {
	new Vue({
		el: '#adminLogin',
		data: {
			formObj: {
			}, // form 表单
		},
		mounted: function() {
			var that = this;
		},
		methods: {
			// 登录				
			fnLogin: function() {
				var that = this;
				if(that.formObj.username != "") {
					if(that.formObj.password != "") {
						//加载层
						var loadingFlag = layer.load(0, {
							shade: false
						}); //0代表加载的风格，支持0-2
						axios({
							url: apiHost + "login/010002",
							data: {
								"requestBody": {
									
								}
							},
							method: "POST",
						}).then(function(res) {
							console.log(res)
							var resultData = res.data.responseBody;
							if(resultData.status.statusCode == '000000') {
								layer.close(loadingFlag); // 关闭加载
								document.getElementById("qrcode").src = resultData.data.qrCodeUrl;
								sessionStorage.setItem("wId", resultData.data.wId);
								setTimeout(function(){
									axios({
										url: apiHost + "login/010003",
										data: {
											"requestBody": {
												wId:resultData.data.wId,
											}
										},
										method: "POST",
									}).then(function(res) {
										console.log(res)
										var resultData = res.data.responseBody;
										if(resultData.status.statusCode == '000000') {
											window.location.href = "index.html";
											layer.close(loadingFlag); // 关闭加载
											layer.msg('获取成功', {
												icon: 1,
												time: 2000
											});
										}//调用成功，跳转到首页并展示
									}).catch(function(res) {
										console.log(res)
										layer.msg('请求失败,请稍候再试', {
											icon: 2,
											time: 2000
										});
										layer.close(loadingFlag); // 关闭加载
									})
								},10000);
								layer.msg('获取成功', {
									icon: 1,
									time: 2000
								});
							}
						}).catch(function(res) {
							console.log(res)
							layer.msg('请求失败,请稍候再试', {
								icon: 2,
								time: 2000
							});
							layer.close(loadingFlag); // 关闭加载
						})
					}
				} 
			},
		},
	})
})
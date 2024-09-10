const trendingList = document.querySelector(".trendingList")
const trendingItemHeight = trendingList.children[0].offsetHeight;
let sliderIntervalID = null;


function initIndexEvent() {
	event_trending_moveSlide(".trending");
	event_trending_stopSlide(".trending");
	event_trending_move(".trending");
	getAllDayOffs();
	updateDate();
	event_component_act();
}

function event_trending_moveSlide(selector) {
	if(sliderIntervalID == null) {
		sliderIntervalID = setInterval(() => {
			$(`${selector} .trendingList`).animate({
				'marginTop':-trendingItemHeight
			}, function() {
				$(".trendingItem:first", this).appendTo(`${selector} .trendingList`);
				$(this).css({"marginTop": "0"});
			})
		}, 2000)		
	}
}

function event_trending_stopSlide(selector) {
	$(`${selector}`).mouseenter(function(event) {
		clearInterval(sliderIntervalID);
		sliderIntervalID = null;
	})
	
	$(`${selector}`).mouseleave(function() {
		event_trending_moveSlide(".trending");
	})
}

function event_trending_move(selector) {
	$(`${selector} .trendingController .goUp`).click(function() {
		$(`${selector} .trendingList`).stop().animate({
			'marginTop':-trendingItemHeight
		}, function() {
			$(".trendingItem:first", this).appendTo(`${selector} .trendingList`);
			$(this).css({"marginTop": "0"});
		})
	})
	$(`${selector} .trendingController .goDown`).click(function() {
		$(`${selector} .trendingList`).stop().animate({
			'marginTop':trendingItemHeight
		}, function() {
			$(".trendingItem:last", this).prependTo(`${selector} .trendingList`);
			$(this).css({"marginTop": "0"});
		})
	})
}

// get all dayoff
function getAllDayOffs() {
	$.ajax({
		type:"post",
		async: false,
		url:`${rootURL}/calendar/getAllDayOffs.do`,
		success: function(data, status) {
			dayOffsMap = JSON.parse(data);
		}
	})
//	location.href = `${rootURL}/calendar/getAllDayOffs.do`;
}

let dayOffDate = new Date();
function updateDate() {
	const year = dayOffDate.getFullYear();
	const month = dayOffDate.getMonth()+1;
	const mm = month < 10 ?  '0'+month : ''+month;
	
	document.querySelector(".dayOff .yyyy").textContent = `${year}`;
	document.querySelector(".dayOff .mm").textContent = `${month}`;
	
	showDayOffs(year+mm);
}

function prevMonth() {
  dayOffDate.setMonth(dayOffDate.getMonth() - 1);
  updateDate();
}

function nextMonth() {
  dayOffDate.setMonth(dayOffDate.getMonth() + 1);
  updateDate();
}

function showDayOffs(yyyymm) {
	let cnt = 0;
	let _dayOffsList = dayOffsMap.dayOffsList;
	const circleTextBox = document.querySelector(".circleTextBox");
	circleTextBox.innerHTML ="";
	
	for(let i = 0; i < _dayOffsList.length; i++) {
		let _yyyymm = _dayOffsList[i].YYYY + _dayOffsList[i].MM;
		if(_yyyymm == yyyymm) {
			let span_circleText = document.createElement("span");
			span_circleText.classList.add("circleText");
			span_circleText.innerText = _dayOffsList[i].DD;
			circleTextBox.appendChild(span_circleText);
			cnt++;
		}
	}
	
	document.querySelector(".dayOff .dayOffsAmount span").textContent = cnt;
}

function event_component_act() {
	$(".dayOff .controller .prev").click(function() {
		prevMonth();
	})
	$(".dayOff .controller .next").click(function() {
		nextMonth();
	})
	$("bky-rounded-button.checkout").click(function() {
		location.href = `${rootURL}/jsp/user/checkout.jsp`;
	})
	$("bky-rounded-button.reservations").click(function() {
		location.href = `${rootURL}/jsp/user/reservation.jsp`;
	})
}
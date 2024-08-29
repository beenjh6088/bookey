const trendingList = document.querySelector("#background .trendingList");
const trendingItemHeight = trendingList.children[0].offsetHeight;
const $trendingList = $(".trendingList");
let sliderIntervalID = null;
let dayOffsMap;

function initIndexEvent() {
	event_trending_moveSlide();
	event_trending_stopSlide();
	event_trending_move();
	getAllDayOffs();
	updateDate();
	event_dayOff_move();
}

function event_trending_moveSlide() {
	if(sliderIntervalID == null) {
		sliderIntervalID = setInterval(() => {
			$trendingList.animate({
				'marginTop':-trendingItemHeight
			}, function() {
				$(".trendingItem:first", this).appendTo(".trendingList");
				$(this).css({"marginTop": "0"});
			})
		}, 2000)		
	}
}

function event_trending_stopSlide() {
	$(".trending").mouseenter(function(event) {
		clearInterval(sliderIntervalID);
		sliderIntervalID = null;
	})
	
	$(".trending").mouseleave(function() {
		event_trending_moveSlide();
	})
}

function event_trending_move() {
	$(".trendingController .goUp").click(function() {
		$trendingList.stop().animate({
			'marginTop':-trendingItemHeight
		}, function() {
			$(".trendingItem:first", this).appendTo(".trendingList");
			$(this).css({"marginTop": "0"});
		})
	})
	$(".trendingController .goDown").click(function() {
		$trendingList.stop().animate({
			'marginTop':trendingItemHeight
		}, function() {
			$(".trendingItem:last", this).prependTo(".trendingList");
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

function event_dayOff_move() {
	$(".dayOff .controller .prev").click(function() {
		prevMonth();
	})
	$(".dayOff .controller .next").click(function() {
		nextMonth();
	})
}
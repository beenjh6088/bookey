const domain = window.location.hostname;
const port = window.location.port;
const pathName = window.location.pathname.split("/")[1];
const rootURL = `http://${domain}:${port}/${pathName}`


function initCommonEvent() {
	letsMoveWithMouse();
}

function letsMoveWithMouse() {
  const circleBox = document.querySelector('.circleTextBox');
  const circles = document.querySelectorAll(".circleTextBox .circleText");

  // value for whether a mouse is being clcked or not
  let isMouseDown = false;
  let startX, scrollLeft;
  
  if(circleBox != null) {
    
    circleBox.addEventListener('mousedown', (e) => {
      isMouseDown = true;
      circles.forEach(c => c.classList.remove("active"));
      e.target.closest(".card") ? e.target.closest(".card").classList.add("active") : null;
      // 드래그를 시작한 지점의 x 좌표
      // the x offSet to start dragging 
      startX = e.pageX - circleBox.offsetLeft;
      // 현재 얼만큼 스크롤되었는지 변수
      // valueable for how far you drag
      scrollLeft = circleBox.scrollLeft;
    });
    
    circleBox.addEventListener('mouseleave', (e) => {
      isMouseDown = false;
      circles.forEach(c => c.classList.remove("active"));
    });
    
    circleBox.addEventListener('mouseup', (e) => {
      isMouseDown = false;
      circles.forEach(c => c.classList.remove("active"));
    });
    
    circleBox.addEventListener('mousemove', (e) => {

      if (!isMouseDown) return;
    
      e.preventDefault();
  
      // 마우스로 클릭한 시점부터 놓기까지의 거리만큼 스크롤로 이동하기
			// scroll the distance from when you click with your mouse to let go  
      const x = e.pageX - circleBox.offsetLeft;
      const walk = (x - startX) * 1;
      circleBox.scrollLeft = scrollLeft - walk;
    });
  }
}

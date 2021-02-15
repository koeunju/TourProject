$(function () {
    $('#tourF').on('submit', () => {
      if(!$('#selTheme').val()) {
          alert('테마를 선택해주세요');
          $('#selTheme').focus();
          return false;
      }
      if(!$('#selLoc').val()) {
          alert('지역을 선택해주세요');
          $('#selLoc').focus();
          return false;
      }
      return true;
    })
})
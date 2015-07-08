/* global define:false */
define(['card/Rank'], function(Rank) {
  function upBy(i, l, r, lowRank) {
    if (lowRank === Rank.ace.value && l === Rank.ace) {
      switch(i) {
        case 1:
          return r === Rank.two;
        case 2:
          return r === Rank.three;
        case 3:
          return r === Rank.four;
        case 4:
          return r === Rank.five;
        default:
          throw '?';
      }
    } else {
      return l.value === r.value - i;
    }
  }

  function downBy(i, l, r, lowRank) {
    if (lowRank === Rank.ace.value && l === Rank.ace) {
      switch(i) {
        case 1:
          return r === Rank.king;
        case 2:
          return r === Rank.queen;
        case 3:
          return r === Rank.jack;
        case 4:
          return r === Rank.ten;
        default:
          throw '?';
      }
    } else {
      return l.value === r.value + i;
    }
  }


  return {
    'rank': function(rule, l, r, lowRank) {
      switch(rule) {
        case 'None':
          return false;
        case 'Up':
          return upBy(1, l, r, lowRank);
        case 'Down':
          return downBy(1, l, r, lowRank);
        case 'Equal':
          return l === r;
        case 'UpOrDown':
          return upBy(1, l, r, lowRank) || downBy(1, l, r, lowRank);
        case 'UpBy2':
          return upBy(2, l, r, lowRank);
        case 'DownBy2':
          return downBy(2, l, r, lowRank);
        case 'UpBy3':
          return upBy(3, l, r, lowRank);
        case 'DownBy3':
          return downBy(3, l, r, lowRank);
        case 'UpBy4':
          return upBy(4, l, r, lowRank);
        case 'DownBy4':
          return downBy(4, l, r, lowRank);
        case 'UpByPileIndex':
          throw '?';
        case 'Any':
          return true;
        default:
          throw '?';
      }
    },

    'suit': function(rule, l, r) {
      switch(rule) {
        case 'None':
          return false;
        case 'SameSuit':
          return l === r;
        case 'DifferentSuits':
          return l !== r;
        case 'SameColor':
          return l.color === r.color;
        case 'AlternatingColors':
          return l.color !== r.color;
        case 'Any':
          return true;
        default:
          throw '?';
      }
    }
  };
});

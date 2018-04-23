/**
 * Traffic Jam Game Solver
 * based on IDA* algorithm implementation v0.5
 * @author: Duc Ngo Minh
 * @date: 2010-09-12
 */
/*Erin O'Brien: Reference https://www.codechef.com/FEB11/problems/RUSHHOUR for 
 explanation of input requirements.  Reference https://discuss.codechef.com/questions/4179/rushhour-editorial
 , Setter's Solution for code attribution*/

 
#include <iostream>
#include <fstream>
#include <cstdio>
#include <vector>
#include <algorithm>
#include <string>
#include <sstream>
#include <iomanip>
#include <cassert>
#include <queue>
#include <ctime>
using namespace std;
 
// Problem specific data structure
 
// Size of board
int nRow, nCol;
 
// Number of blocks
int nBlock;
 
int goalCol;
 
struct Cell {
  int row, col;
  Cell(int row, int col) {
    this->row = row;
    this->col = col;
  }
 
  string toString() {
    ostringstream os;
    os << "(" << row+1 << ", " << col+1 << ")";
    return os.str();
  }
};
 
// Block is just a list of cells
typedef vector<Cell> Block;
 
const int EMPTY = -1;
 
typedef vector< vector<int> > Board;
 
void initBoard(Board &b, int nRow, int nCol) {
  b.resize(nRow);
  for (int i=0; i<nRow; ++i) {
    b[i].resize(nCol);
  }
  for (int i=0; i<nRow; ++i) {
    for (int j=0; j<nCol; ++j) {
      b[i][j] = EMPTY;
    }
  }
}
 
void clearCell(Board &b, int row, int col) {
  b[row][col] = EMPTY;
}
 
// Determine if a block is horizontal
// Applicable to Traffic Jam only, when blocks are either vertical or horizontal
bool horizontal(Block b) {
  if (b.size() <= 1) {
    cerr << "Block cannot have size <= 1!" << endl;
    exit(1);
  }
 
  if (b[0].row == b[1].row) {
    return true;
  }
 
  return false;
}
 
const int MAX_ROW = 10;
const int MAX_COL = 10;
 
// Modify State, Action and StateIter to
// represent problem specific data
 
/**
 * State: board mask array and list of blocks
 */
struct State {
  // board[i][j] : id of block occupying cell (i,j)
  Board board;
  vector<Block> blockList;
 
  State() {
    initBoard(board, nRow, nCol);
  }
 
  string toString() {
    ostringstream os;
    os << "Board:" << endl;
    for (int i=0; i<nRow; ++i) {
      for (int j=0; j<nCol; ++j) {
        if (board[i][j] == EMPTY) {
          os << ".";
        } else {
          os << (char) (board[i][j]+'a');
        }
      }
      os << endl;
    }
    for (int i=0; i < (int) blockList.size(); ++i) {
      os << "Block " << (char) (i+'a') << ": ";
      Block b = blockList[i];
      for (typeof (b.begin()) it = b.begin(); it!=b.end(); ++it) {
        os << it->toString() << " ";
      }
      os << endl;
    }
    return os.str();
  }
};
 
/**
 * Action: move a block by a vector
 */
struct Action {
  int blockId;
  int dRow;
  int dCol;
 
  Action() {}
 
  Action(int blockId, int dRow, int dCol) {
    this->blockId = blockId;
    this->dRow = dRow;
    this->dCol = dCol;
  }
 
  string toString() {
    ostringstream os;
    os << "Move block " << (char) (blockId + 'a') << " by (" << dRow << ", " << dCol << ")" << endl;
    return os.str();;
  }
};
 
struct Arc {
  Action action;
  int cost;
};
 
// Move the corresponding block specified by the action and update the state
State applyAction(State state, Action action) {
  State next;
 
  next = state;
 
  Block &b = next.blockList[action.blockId];
 
  // Remove mask from old block positions
  for (typeof (b.begin()) it = b.begin(); it != b.end(); ++it) {
    clearCell(next.board, it->row, it->col);
 
    // & update block cells
    it->row += action.dRow;
    it->col += action.dCol;
  }
 
  // Update mask as new block position
  for (typeof (b.begin()) it = b.begin(); it != b.end(); ++it) {
    next.board[it->row][it->col] = action.blockId;
  }
 
  return next;
}
 
bool applicable(State state, Action action) {
  if (action.blockId < 0 || action.blockId >= nBlock) {
    cerr << "Invalid block id" << endl;
    exit(1);
  }
 
  Block &b = state.blockList[action.blockId];
 
  for (typeof (b.begin()) it = b.begin(); it != b.end(); ++it) {
    int newRow = it->row + action.dRow;
    int newCol = it->col + action.dCol;
 
    if (newRow < 0 || newRow>=nRow || newCol < 0 || newCol>=nCol) {
      //cerr << "Cell outside range!" << endl;
      return false;
    }
 
    if (state.board[newRow][newCol] != EMPTY && state.board[newRow][newCol] != action.blockId) {
      //cerr << "Move to non-empty space!" << endl;
      return false;
    }
  }
 
  return true;
}
 
/**
 * Iterator to systematically enumerate applicable actions
 */
struct StateIter {
  State state;
 
  // Iterator's internal data structure
 
  int blockId;
  int direction;
  int length;
 
  StateIter(State state) {
    this->state = state;
    blockId = 0;
    direction = 0;
    length = 1;
  }
 
  void advance() {
    length = 1;
    if (direction < 1) {
      ++direction;
    } else {
      ++blockId;
      direction = 0;
    }
  }
 
  Action action;
 
  void computeAction() {
    Block & b = state.blockList[blockId];
 
    // block's orientation
    bool horiz = horizontal(b);
 
    action.blockId = blockId;
 
    if (horiz) {
      action.dRow = 0;
      if (direction == 0) {
        action.dCol = - length;
      } else if (direction == 1) {
        action.dCol = length;
      }
    } else {
      action.dCol = 0;
      if (direction == 0) {
        action.dRow = - length;
      } else if (direction == 1) {
        action.dRow = length;
      }
    }
  }
 
  bool hasNext() {
    while (true) {
      if (blockId == nBlock) {
        return false;
      }
      computeAction();
      if (applicable(state, action)) {
        ++length;
        return true;
      }
      advance();
    }
  }
 
  /**
   * Return the next outgoing arc
   */
  Arc next() {
    Arc arc;
 
    arc.action = action;
    arc.cost = 1;
 
    return arc;
  }
};
 
bool goalTest(State state) {
  Block president = state.blockList[0];
 
  int len = president.size();
  if (president[len-1].col == nCol-1) {
    return true;
  }
 
  return false;
}
 
/**
 * Return an admissible heuristic value of a state
 * (an estimate of cost to goal which is not more than the real cost)
 */
int heuristic(State state) {
  if (goalTest(state)) {
    return 0;
  }
 
  Block & president = state.blockList[0];
 
  int len = president.size();
 
  int row = president[len-1].row;
  int col = president[len-1].col;
 
  queue<Cell> needEmpty;
  vector< vector<bool> > markEmpty;
  markEmpty.resize(nRow);
  for (int i=0; i<nRow; ++i) {
    markEmpty[i].resize(nCol);
  }
 
  for (int c = col+1; c<nCol; ++c) {
    if (state.board[row][c] != EMPTY) {
      needEmpty.push(Cell(row, c));
      markEmpty[row][c] = true;
    }
  }
 
  vector<bool> needMove;
 
  needMove.resize(nBlock);
 
  needMove[0] = true;
 
//  cerr << state.toString();
 
  while (!needEmpty.empty()) {
    Cell c = needEmpty.front();
    needEmpty.pop();
 
    int blockId = state.board[c.row][c.col];
 
//    cerr << "Block " << (char) (blockId + 'a') << " needs to move!" << endl;
 
    Block & b = state.blockList[blockId];
 
    needMove[blockId] = true;
 
    bool horiz = horizontal(b);
 
    int place, leftMost, rightMost;
    if (horiz) {
      place = c.col;
      rightMost = nCol - 1;
      leftMost = 0;
 
      int cr = c.row;
      for (int cc=rightMost; state.board[cr][cc] != blockId; --cc) {
        if (state.board[cr][cc] != EMPTY) {
          Block & b2 = state.blockList[state.board[cr][cc]];
          if (horizontal(b2)) {
            --rightMost;
          }
        }
      }
    } else {
      place = c.row;
      rightMost = nRow - 1;
      leftMost = 0;
    }
 
    int sz = b.size();
 
    int left=place - sz;
    int right = place + sz;
 
    int cr, cc;
    if (horiz) {
      cr = c.row;
    } else {
      cc = c.col;
    }
 
    if (left < leftMost && right <= rightMost) {
      for (int t = place; t <= right; ++t) {
        if (horiz) {
          cc = t;
        } else {
          cr = t;
        }
        if (state.board[cr][cc] != EMPTY && state.board[cr][cc] != blockId && !markEmpty[cr][cc]) {
          markEmpty[cr][cc] = true;
          needEmpty.push(Cell(cr, cc));
        }
      }
    } else if (left >= leftMost && right > rightMost) {
      for (int t=left; t<=place; ++t) {
        if (horiz) {
          cc = t;
        } else {
          cr = t;
        }
        if (state.board[cr][cc] != EMPTY && state.board[cr][cc] != blockId && !markEmpty[cr][cc]) {
          markEmpty[cr][cc] = true;
          needEmpty.push(Cell(cr, cc));
        }
      }
    } else if (left < leftMost && right > rightMost) {
      cerr << "Left: " << left;
      cerr << " Leftmost: " << leftMost;
      cerr << " Right: " << right;
      cerr << " Rightmost: " << rightMost;
      cerr << " No way to move to get the empty square!" << endl;
      exit(1);
    } else {
//      cerr << "Both way are ok. No information." << endl;
    }
  }
 
  int cnt = 0;
  for (int i=0; i<nBlock; ++i) {
    if (needMove[i]) {
      ++cnt;
    }
  }
 
  return cnt;
}
 
const int FOUND_GOAL = -1;
 
const int INF = 1000000000;
 
vector<Arc> solution;
int solutionCost;
 
/**
 * An enhanced dfs procedure
 * Travel through all the nodes with f-value within [cutoff]
 */
int dfsAux(State node, int cost, int cutoff) {
  int f = cost + heuristic(node);
 
  if (f > cutoff) {
    return f;
  }
 
  if (goalTest(node)) {
    solutionCost = f;
    return FOUND_GOAL;
  }
 
  StateIter it(node);
 
  int nextCutoff = INF;
 
  while (it.hasNext()) {
    Arc arc = it.next();
    State nextState = applyAction(node, arc.action);
 
    int temp2 = dfsAux(nextState, cost + arc.cost, cutoff);
 
    if (temp2 == FOUND_GOAL) {
      solution.push_back(arc);
      return FOUND_GOAL;
    }
 
    nextCutoff = min(nextCutoff, temp2);
  }
 
  return nextCutoff;
}
 
/**
 * ID A* algorithm
 * Return true if a solution is found
 * Solution is represented as a sequence of arcs
 * in vector [solution]
 */
bool idAStar(State initial) {
  int cutoff = 0 + heuristic(initial);
 
  solution.clear();
  solutionCost = INF;
 
  while (1) {
//    cerr << "Start an iteration with cutoff value " << cutoff << "..." << endl;
    int temp = dfsAux(initial, 0, cutoff);
    if (temp == FOUND_GOAL) {
      return true;
    }
    if (temp == INF) {
      return false;
    }
    cutoff = temp;
  }
}
 
State initial;
 
void printSolution() {
  printf("Solution\n");
  printf("Total steps: %d\n", solution.size());
  printf("Total cost: %d\n", solutionCost);
 
  State s = initial;
 
  for ( typeof (solution.rbegin()) it = solution.rbegin(); it!=solution.rend(); ++it) {
    printf("State: %s\n", s.toString().c_str());
    printf("Action: %s\n", (it->action).toString().c_str());
    s = applyAction(s, it->action);
  }
}
 
// TESTING, INPUT, OUTPUT
 
State readState(istream &is) {
  is >> nRow >> nCol >> nBlock;
 
  State state;
  state.blockList.resize(nBlock);
 
  for (int i=0; i<nRow; ++i) {
    string s;
    is >> s;
 
    for (int j=0; j<nCol; ++j) {
      if (s[j] != '.') {
        int blockId = s[j] - 'a'; // 'a'..'z'
 
        state.blockList[blockId].push_back(Cell(i,j));
 
        state.board[i][j] = blockId;
      }
    }
  }
 
  return state;
}
 
void testInput() {
  State s = readState(cin);
  cout << s.toString();
}
 
Action readAction(istream & is) {
  Action a;
  is >> a.blockId >> a.dRow >> a.dCol;
  return a;
}
 
void testAction() {
  State s = readState(cin);
  Action a = readAction (cin);
  s = applyAction(s, a);
  cout << s.toString();
}
 
void testApplicable() {
  State s = readState(cin);
  Action a = readAction (cin);
  cout << s.toString();
  bool b = applicable(s,a);
  if (b) {
    cout << "OK!" << endl;
  } else {
    cout << "Not OK!" << endl;
  }
}
 
void testNext() {
  State s = readState(cin);
  StateIter it(s);
  while (it.hasNext()) {
    Arc a = it.next();
    State nextS = applyAction(s, a.action);
    cout << a.action.toString() << endl;
    cout << nextS.toString() << endl;
  }
}
 
void testHeuristic() {
  State s = readState(cin);
  int h = heuristic(s);
  cout << h << endl;
}
 
/**
 * Main program
 */
int main() {
  testInput();
  testAction();
  testApplicable();
  testNext();
  testHeuristic();
 
  int nTest;
  cin >> nTest;
  
  
  
  while (nTest--) {
    initial = readState(cin);
    unsigned int start = clock();
    idAStar(initial);
    cout << "Time taken in millisecs: " << clock()-start;
    printSolution();
    cout << solutionCost << endl;
  }
 
  return 0;
}
 
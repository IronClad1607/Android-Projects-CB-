package com.example.othello

import android.graphics.Color

fun updateBoard(
    btnArray: ArrayList<ArrayList<CustomButton>>,
    blackTurn: Boolean
): Int {
    var counter = 0
    var valid = 0
    var i: Int
    var j: Int
    var flag: Boolean
    for (olc in 0..7) {
        for (ilc in 0..7) {
            if (btnArray[olc][ilc].clicked)
                continue
            if (blackTurn) {
                i = olc + 1
                j = ilc + 1
                flag = false
                valid = 0

                //Diagonal Right - Down
                while (i < 8 && j < 8 && btnArray[i][j].clicked) {
                    if (btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (!btnArray[i][j].black) {
                        valid = 1
                    }
                    if (valid == 1 && btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i++
                    j++
                }
                i = olc - 1
                j = ilc - 1
                valid = 0

                //Up Left
                while (i >= 0 && j >= 0 && !flag && btnArray[i][j].clicked) {
                    if (btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (!btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i--
                    j++
                }
                i = olc - 1
                j = ilc + 1
                valid = 0
                //Up Right
                while (i >= 0 && j < 8 && !flag && btnArray[i][j].clicked) {
                    if (btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (!btnArray[i][j].black) {
                        valid = 1
                    }
                    if (valid == 1 && btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i--
                    j++
                }
                i = olc + 1
                j = ilc - 1
                //Down Left
                while (i < 8 && j >= 0 && !flag && btnArray[i][j].clicked) {
                    if (btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (!btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i++
                    j--
                }
                i = olc - 1
                j = ilc
                valid = 0

                //Up
                while (i >= 0 && !flag && btnArray[i][j].clicked) {
                    if (btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (!btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i--
                }
                i = olc + 1
                j = ilc
                valid = 0

                //Down
                while (i < 8 && !flag && btnArray[i][j].clicked) {
                    if (btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (!btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i++
                }
                i = olc
                j = ilc - 1
                valid = 0

                //Left
                while (j >= 0 && !flag && btnArray[i][j].clicked) {
                    if (btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (!btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    j--
                }
                i = olc
                j = ilc + 1
                valid = 0


                //Right
                while (j < 8 && !flag && btnArray[i][j].clicked) {
                    if (btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (!btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    j++
                }
            } else {
                i = olc + 1
                j = ilc + 1
                flag = false

                //Diagonal Right Down
                while (i < 8 && j < 8 && btnArray[i][j].clicked) {
                    if (!btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && !btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i++
                    j++
                }

                i = olc - 1
                j = ilc - 1
                valid = 0

                //Up Left
                while (i >= 0 && j >= 0 && !flag && btnArray[i][j].clicked) {
                    if (!btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && !btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i--
                    j--
                }
                i = olc - 1
                j = ilc + 1
                valid = 0

                //Up Right
                while (i >= 0 && j < 8 && !flag && btnArray[i][j].clicked) {
                    if (!btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (btnArray[i][j].black) {
                        valid = 1
                    }
                    if (valid == 1 && !btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i--
                    j++
                }
                i = olc + 1
                j = ilc - 1
                valid = 0

                //Down Left
                while (i < 8 && j >= 0 && !flag && btnArray[i][j].clicked) {
                    if (!btnArray[i][j].black && valid == 0)
                        break
                    if (btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && !btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i++
                    j--
                }
                i = olc - 1
                j = ilc
                valid = 0

                //Up
                while (i >= 0 && !flag && btnArray[i][j].clicked) {
                    if (!btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && !btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i--
                }
                i = olc + 1
                j = ilc
                valid = 0

                //Down
                while (i < 8 && !flag && btnArray[i][j].clicked) {
                    if (!btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && !btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    i++
                }
                i = olc
                j = ilc - 1
                valid = 0

                //Left
                while (j >= 0 && !flag && btnArray[i][j].clicked) {
                    if (!btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && !btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    j--
                }
                i = olc
                j = ilc + 1
                valid = 0

                //Right
                while (j < 8 && !flag && btnArray[i][j].clicked) {
                    if (!btnArray[i][j].black && valid == 0) {
                        break
                    }
                    if (btnArray[i][j].black)
                        valid = 1
                    if (valid == 1 && !btnArray[i][j].black) {
                        flag = true
                        break
                    }
                    j++
                }
            }
            if (flag) {
                btnArray[olc][ilc].isCanClicked = true
                btnArray[olc][ilc].setBackgroundColor(Color.YELLOW)
                counter++
            } else {
                btnArray[olc][ilc].isCanClicked = false

            }
        }
    }
    return counter
}
mov d,0
mov a,ff
mov [d],a
inc d
mov a,81
mov [d],a
inc d
mov a,ad
mov [d],a
inc d
mov a,85
mov [d],a
inc d
mov [d],a
inc d
mov a,ad
mov [d],a
inc d
mov a,81
mov [d],a
inc d
mov a,ff
mov [d],a
mov a,0
mov b,1
mov d,0
mov {b},[d]
inc d
shl b,1
cmp a,b
jne 1a
mov a,0
mov b,ff
mov {b},a
jmp 17
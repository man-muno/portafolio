% Based on a template by Pasi Eronen (pe at iki dot fi) for T-110.551 spring 2004
% Modified by Jan Hlinovsky for T-110.557 fall 2004

\NeedsTeXFormat{LaTeX2e}

% Basic stuff
\LoadClass[10pt,twocolumn,a4paper]{article}
\RequirePackage{times}
\RequirePackage[T1]{fontenc}
\RequirePackage[dvips]{graphicx}

% Adjust margins
% \addtolength{\textwidth}{14mm}
% \addtolength{\hoffset}{-7mm}
\addtolength{\textheight}{35mm}
\addtolength{\voffset}{-16mm}

% Make the column separator a bit wider
\addtolength{\columnsep}{2mm}  

% Abbreviated bibliography style
\bibliographystyle{abbrv}


% Make a 11 pt font size for subsection headings
% Borrowed from http://www.sims.berkeley.edu/~christin/templates/usenix.sty
\def\subsize{\@setsize\subsize{11pt}\xipt\@xipt}


% Make section headings bold and sans serif.

% 12 pt font size
\renewcommand\section{\@startsection {section}{1}{\z@}%
                                   {-3.5ex \@plus -1ex \@minus -.2ex}%
                                   {2.3ex \@plus.2ex}%
                                   {\normalfont\large\bf\sffamily}}

% 11 pt font size
\renewcommand{\subsection}{\@startsection{subsection}{2}{\z@}%
                                     {-3.25ex\@plus -1ex \@minus -.2ex}%
                                     {1.5ex \@plus .2ex}%
                                     {\reset@font\subsize\bf\sffamily}}

% 10 pt font size
\renewcommand{\subsubsection}{\@startsection{subsubsection}{3}{\z@}%
                                     {-3.25ex\@plus -1ex \@minus -.2ex}%
                                     {1.5ex \@plus .2ex}%
                                     {\reset@font\normalsize\bf\sffamily}}


% Set headers and footers (depending on "cameraready" option)
\RequirePackage{fancyhdr}
\newif\if@cameraready
\@camerareadyfalse
\DeclareOption{cameraready}{\@camerareadytrue}
\ProcessOptions

%isodate from manuals of University of Oulu, time removed
%http://www.oulu.fi/atkk/tkpalv/unix/isodate.html
%referred 2004-02-18
%added and dates in the document changed by Nadja Kasinskaja

\newcommand{\twodigits}[1]{\ifnum #1<10 0\fi \number#1}

\newcommand{\isodate}{%
  \the\year-\twodigits{\month}-\twodigits{\day}}

%endof isodate

\pagestyle{fancyplain}
%\addtolength{\headsep}{-8pt} %oli: -10
\fancyhead{}
\fancyfoot{}
\if@cameraready
  \fancyhead[RO]{\fancyplain{}{HIIT, 2004-12-01}}
  \fancyfoot[CO]{\thepage} 
\else
  \fancyhead[RO]{\fancyplain{}}    
  \fancyfoot[CO]{\thepage}
\fi
\date{}

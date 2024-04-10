// lib/FFmpeg.js
import {FFmpegKit, FFmpegKitConfig, ReturnCode} from 'ffmpeg-kit-react-native';
import RNFS from 'react-native-fs';

import {FRAME_PER_SEC, FRAME_WIDTH} from '../components/MPeg';

class FFmpegWrapper {
  static getFrames(
    localFileName,
    videoURI,
    frameNumber,
    successCallback,
    errorCallback,
  ) {
    let outputImagePath = `${RNFS.CachesDirectoryPath}/${localFileName}_%4d.png`;
    const ffmpegCommand = `-ss 0 -i ${videoURI} -vf "fps=${FRAME_PER_SEC}/1:round=up,scale=${FRAME_WIDTH}:-2" -vframes ${frameNumber} ${outputImagePath}`;

    FFmpegKit.executeAsync(
      ffmpegCommand,
      async session => {
        const state = FFmpegKitConfig.sessionStateToString(
          await session.getState(),
        );
        const returnCode = await session.getReturnCode();
        const failStackTrace = await session.getFailStackTrace();
        const duration = await session.getDuration();

        if (ReturnCode.isSuccess(returnCode)) {
          console.log(
            `Encode completed successfully in ${duration} milliseconds;.`,
          );
          console.log(`Check at ${outputImagePath}`);
          successCallback(outputImagePath);
        } else {
          console.log('Encode failed. Please check log for the details.');
          console.log(
            `Encode failed with state ${state} and rc ${returnCode}.${
              (failStackTrace, '\\n')
            }`,
          );
          errorCallback();
        }
      },
      log => {
        console.log(log.getMessage());
      },
      statistics => {
        console.log(statistics);
      },
    ).then(session =>
      console.log(
        `Async FFmpeg process started with sessionId ${session.getSessionId()}.`,
      ),
    );
  }

  static clipFrames(fileName, videoURI, from, to, textUser){
    let outputImagePath = `file:///storage/emulated/0/Android/data/com.awesomeproject/cache/temp/19999.gif`;

    let y = `file:///storage/emulated/0/Android/data/com.awesomeproject/cache/temp/1709132994682.mp4`;
    ffmpegCommand = `ffmpeg -ss ${from} -t 3 -i ${y} -vf "fps=10,scale=320:-1:flags=lanczos,split[s0][s1];[s0]palettegen[p];[s1][p]paletteuse" -loop 0 ${x}`
    

  
    FFmpegKit.executeAsync(
      ffmpegCommand,
      async session => {
        const state = FFmpegKitConfig.sessionStateToString(
          await session.getState(),
        );
        const returnCode = await session.getReturnCode();
        const failStackTrace = await session.getFailStackTrace();
        const duration = await session.getDuration();

        if (ReturnCode.isSuccess(returnCode)) {
          console.log(
            `Encode completed successfully in ${duration} milliseconds;.`,
          );
          console.log(`Check at ${outputImagePath}`);
          successCallback(outputImagePath);
        } else {
          console.log('Encode failed. Please check log for the details.');
          console.log(
            `Encode failed with state ${state} and rc ${returnCode}.${
              (failStackTrace, '\\n')
            }`,
          );
          errorCallback();
        }
      },
      log => {
        console.log(log.getMessage());
      },
      statistics => {
        console.log(statistics);
      },
    ).then(session =>
      console.log(
        `Async FFmpeg process started with sessionId ${session.getSessionId()}.`,
      ),
    );
  }
}

export default FFmpegWrapper;
